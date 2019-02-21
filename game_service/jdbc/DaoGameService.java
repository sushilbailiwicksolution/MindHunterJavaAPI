package com.bailiwick.game_service.jdbc;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.bailiwick.game_service.resultExtracter.IntegerValueExtracter;
import com.bailiwick.game_service.resultExtracter.SingleIntegerValueExtracter;
import com.bailiwick.game_service.resultExtracter.StringValueResultExtracter;
import com.bailiwick.game_service.resultExtracter.UserDetailResultExtracter;
import com.bailiwick.game_service.util.Queries;
import com.bailiwick.game_servicei.mapper.ActivePlayerDetailsRowMapper;
import com.bailiwick.game_servicei.mapper.InstUsersMapper;
import com.bailiwick.game_servicei.mapper.InstanceMapper;
import com.bailiwick.game_servicei.mapper.PlayerDataRowMapper;
import com.bailiwick.game_servicei.mapper.TempRowMapper;
import com.bailiwick.game_servicei.mapper.WinnerDetailRowMapper;
import com.bailiwick.game_servicei.model.ActivePlayerDetails;
import com.bailiwick.game_servicei.model.GameInstance;
import com.bailiwick.game_servicei.model.GameInstanceResponse;
import com.bailiwick.game_servicei.model.GameInstanceUsers;
import com.bailiwick.game_servicei.model.GamePlayRequest;
import com.bailiwick.game_servicei.model.PackListDetail;
import com.bailiwick.game_servicei.model.PaytmRequest;
import com.bailiwick.game_servicei.model.PlayerDataDetail;
import com.bailiwick.game_servicei.model.PlayerDetail;
import com.bailiwick.game_servicei.model.PriceRequest;
import com.bailiwick.game_servicei.model.Qimage;
import com.bailiwick.game_servicei.model.SaveAnswerDetail;
import com.bailiwick.game_servicei.model.SupportQueryDetails;
import com.bailiwick.game_servicei.model.UserLoginRequest;
import com.bailiwick.game_servicei.model.WinnerDetail;
import com.bailiwick.game_servicei.model.WinnerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.PreparedStatement;

@Service
public class DaoGameService {

	private Logger logger = (Logger) LogManager.getLogger(getClass());

	@Autowired
	JDBCTemplateService jdbcService;
	@Autowired
	Queries queries;

//	public List<ResGameDetails> getGames() {
//		
//		List<ResGameDetails> gameDetails = null;
//
//		try {
//			String query = queries.selectAllGames();
//			gameDetails = jdbcService.getJdbcTemplate().query(query, 
//					new Object[] {}, 
//					new GameRowMapper());
//
//			return gameDetails;
//		}catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new ArrayList<>();
//		}
//	}
//	
//	public List<SpinBlocks> getSpinBlocks(String gameId) {
//		
//		List<SpinBlocks> gameDetails = null;
//		
//		try {
//			String query = queries.getSpinBlocks();
//			gameDetails = jdbcService.getJdbcTemplate().query(query, 
//					new Object[] {gameId}, 
//					new SpinGameRowMapper());
//			
//			return gameDetails;
//		}catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new ArrayList<>();
//		}
//	}
//	
//	public List<ResGameDetails> getGames(String gameId) {
//		
//		List<ResGameDetails> gameDetails = null;
//		
//		try {
//			String query = queries.selectGameByGameId();
//			gameDetails = jdbcService.getJdbcTemplate().query(query, 
//					new Object[] {gameId}, 
//					new GameRowMapper());
//			
//			return gameDetails;
//		}catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			return new ArrayList<>();
//		}
//	}

	public Integer getNumberOfPlayer(PriceRequest priceRequest) {
		Integer result = null;
		try {
			// String query = queries.selectGameByGameId();
			result = jdbcService.getJdbcTemplate().query(
					"select noOfusers from mh_game_instance where id  = " + priceRequest.getInstanceId() + "",
					new SingleIntegerValueExtracter());

			// return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// return new ArrayList<>();
		}
		return result;
	}

	public Integer getCorrectAnswerCount(PlayerDetail playerDetail) {
		Integer result = null;
		try {
			// String query = queries.selectGameByGameId();
			result = jdbcService.getJdbcTemplate()
					.query("select correctAnswerCount as noOfusers  from mh_active_game_details where userId = "
							+ playerDetail.getUserId() + " and gameId = " + playerDetail.getGameId() + " and packId = "
							+ playerDetail.getPackId() + ";", new SingleIntegerValueExtracter());

			// return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// return new ArrayList<>();
		}
		return result;
	}

	public WinnerResponse getwinnerDetail(PlayerDetail playerDetail) {
		WinnerResponse winnerResponse = new WinnerResponse();
		List<WinnerDetail> lstPlayerDetail = new ArrayList();
		String winnerQuery = "SELECT `mh_active_game_details`.`correctAnswerCount`,`mh_user_access_details`.`username` FROM mh_active_game_details LEFT JOIN mh_user_access_details ON mh_active_game_details.`userId`=mh_user_access_details.`user_id`  WHERE mh_active_game_details.`gameId` = "
				+ playerDetail.getGameId() + " AND mh_active_game_details.`packId` = " + playerDetail.getPackId()
				+ " AND mh_active_game_details.`instanceId` = " + playerDetail.getInstanceId()
				+ "  ORDER BY mh_active_game_details.`updatedDate` DESC ";
		// String packId = (new StringBuilder()).append("SELECT `entryfee` FROM
		// `api_packs_details` WHERE
		// `packId`=").append(gamePlayRequest.getPackId()).append("").toString();
		// System.out.println((new StringBuilder()).append("game play request for cash
		// blast ==>>").append(gamePlayRequest).toString());
		lstPlayerDetail = jdbcService.getJdbcTemplate().query(winnerQuery, new WinnerDetailRowMapper());
		winnerResponse.setLstPlayerDetail(lstPlayerDetail);
		System.out.println("");
		int update = jdbcService.getJdbcTemplate().update(
				"INSERT INTO mh_user_reward_details(user_id, reward_id, game_id,pack_id,win_date) SELECT `userId`, `instanceId`, `gameId`,`packId`,`updatedDate` FROM `mh_active_game_details`  WHERE mh_active_game_details.`gameId` = "
						+ playerDetail.getGameId() + " AND mh_active_game_details.`packId` = "
						+ playerDetail.getPackId() + " AND mh_active_game_details.`instanceId` = "
						+ playerDetail.getInstanceId()
						+ "  ORDER BY mh_active_game_details.`updatedDate` DESC LIMIT 1");
		if (update > 0) {
			String batchSql[] = new String[4];
			batchSql[0] = "BEGIN";
			batchSql[1] = "INSERT INTO `mh_game_player_detail_history` (SELECT * FROM `mh_active_game_details`  WHERE mh_active_game_details.`gameId` = "
					+ playerDetail.getGameId() + " AND mh_active_game_details.`packId` = " + playerDetail.getPackId()
					+ " AND mh_active_game_details.`instanceId` = " + playerDetail.getInstanceId() + ")";
			batchSql[2] = "DELETE FROM `mh_active_game_details` WHERE mh_active_game_details.`gameId` = "
					+ playerDetail.getGameId() + " AND mh_active_game_details.`packId` = " + playerDetail.getPackId()
					+ " AND mh_active_game_details.`instanceId` = " + playerDetail.getInstanceId() + "";
			batchSql[3] = "COMMIT";
			jdbcService.getJdbcTemplate().batchUpdate(batchSql);
		}
		// TODO Auto-generated method stub
		return winnerResponse;
	}

	public void increseCorrectAnswerCount(SaveAnswerDetail saveAnswerDetail) {
		String query = null;

		if (saveAnswerDetail.getGameId().equalsIgnoreCase("1")) {
			query = " UPDATE mh_active_game_details SET mh_active_game_details.correctAnswerCount = correctAnswerCount + 1,updatedDate = '"
					+ (new Timestamp(System.currentTimeMillis())).toString() + "'   WHERE `userId` = "
					+ saveAnswerDetail.getuId() + " AND `gameId` =" + saveAnswerDetail.getGameId()
					+ " AND `instanceId` = " + saveAnswerDetail.getGameInstanceId() + " AND `packId` = "
					+ saveAnswerDetail.getPackId() + "";
			try {
				System.out.println((new StringBuilder()).append("increase answer query ==>").append(query).toString());
				jdbcService.getJdbcTemplate().update(query);
				timeTaken(saveAnswerDetail, 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			if ((saveAnswerDetail.getGameId() != null) && (saveAnswerDetail.getGameId().equalsIgnoreCase("4"))) {
				saveAnswerDetail.setGameInstanceId("0");
				query = " UPDATE mh_active_game_details SET mh_active_game_details.`correctAnswerCount` = correctAnswerCount + 1,updatedDate = '"
						+ new Timestamp(System.currentTimeMillis()).toString() + "'   WHERE `userId` = "
						+ saveAnswerDetail.getuId() + " AND `gameId` =" + saveAnswerDetail.getGameId()
						+ " AND `instanceId` = " + saveAnswerDetail.getGameInstanceId() + " AND `packId` = "
						+ saveAnswerDetail.getPackId() + "";
			}
			if (((saveAnswerDetail.getGameId() != null) && (saveAnswerDetail.getGameId().equalsIgnoreCase("3")))) {
				System.out.println("inside query preparation");
				query = " UPDATE mh_active_game_details SET mh_active_game_details.`correctAnswerCount` = correctAnswerCount + 1, score = score+"+saveAnswerDetail.getScoreCrieteria()+",updatedDate = '"
						+ new Timestamp(System.currentTimeMillis()).toString() + "'   WHERE `userId` = "
						+ saveAnswerDetail.getuId() + " AND `gameId` =" + saveAnswerDetail.getGameId()
						+ "  AND `packId` = " + saveAnswerDetail.getPackId() + "";
			saveTodayScore(saveAnswerDetail);
			} 

			try {
				System.out.println("increase answer query ==>" + query);

				jdbcService.getJdbcTemplate().update(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private void saveTodayScore(SaveAnswerDetail saveAnswerDetail) {
		try {
			System.out.println("inside today score " + saveAnswerDetail);
			Integer score = 0;
			String query = "select score as noOfusers from mh_game_today_score where userId = \"+saveAnswerDetail.getuId()+\" and packId = \"+saveAnswerDetail.getPackId()+\" and scoreDate = '\"+System.currentTimeMillis()+\"'";
			System.out.println("date ==>" + System.currentTimeMillis());
			score = jdbcService.getJdbcTemplate().query("select score as noOfusers from mh_game_today_score where userId = "+saveAnswerDetail.getuId()+" and packId = "+saveAnswerDetail.getPackId()+" and scoreDate = '"+java.time.LocalDate.now()+"' ",new IntegerValueExtracter());
		System.out.println("query ==>" +query );
			System.out.println("score value " +score );
			if (score!=null) {
				score = score+saveAnswerDetail.getScoreCrieteria();
				jdbcService.getJdbcTemplate().update("update mh_game_today_score  set  score = "+score+"  where userId = "+saveAnswerDetail.getuId()+"  and scoreDate = '"+LocalDate.now().toString()+"'");

			}
			else {
				score = saveAnswerDetail.getScoreCrieteria();
				jdbcService.getJdbcTemplate().update("INSERT INTO mh_game_today_score (userId,gameId,packId,score, scoreDate)VALUES(?,?,?,?,?)", new Object[] {saveAnswerDetail.getuId(),saveAnswerDetail.getGameId(),saveAnswerDetail.getPackId(),score, new java.sql.Date(System.currentTimeMillis())});

			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void increseWrongAnswerCount(SaveAnswerDetail saveAnswerDetail) {
		String query = null;
		if ((saveAnswerDetail.getGameId().equalsIgnoreCase("3"))
				|| (saveAnswerDetail.getGameId().equalsIgnoreCase("4"))) {
			saveAnswerDetail.setGameInstanceId("0");
		}

		query = " UPDATE mh_active_game_details SET mh_active_game_details.wrongAnswerCount = wrongAnswerCount  + 1,updatedDate = '"
				+ (new Timestamp(System.currentTimeMillis())).toString() + "'   WHERE `userId` = "
				+ saveAnswerDetail.getuId() + " AND `gameId` =" + saveAnswerDetail.getGameId() + " AND `instanceId` = "
				+ saveAnswerDetail.getGameInstanceId() + " AND `packId` = " + saveAnswerDetail.getPackId() + "";
		try {
			System.out.println((new StringBuilder()).append("increase answer query ==>").append(query).toString());
			jdbcService.getJdbcTemplate().update(query);
			timeTaken(saveAnswerDetail, 2);
			if ((saveAnswerDetail.getGameId() != null) && (saveAnswerDetail.getGameId().equalsIgnoreCase("5"))) {
				String[] batchSql = new String[4];
				batchSql[0] = "BEGIN";
				batchSql[1] = ("INSERT INTO `mh_game_player_detail_history` SELECT * FROM `mh_active_game_details`  WHERE mh_active_game_details.`gameId` = "
						+ saveAnswerDetail.getGameId() + " AND mh_active_game_details.`packId` = "
						+ saveAnswerDetail.getPackId() + " AND mh_active_game_details.`userId` = "
						+ saveAnswerDetail.getuId() + "");
				batchSql[2] = ("DELETE FROM `mh_active_game_details` WHERE mh_active_game_details.`gameId` = "
						+ saveAnswerDetail.getGameId() + " AND mh_active_game_details.`packId` = "
						+ saveAnswerDetail.getPackId() + " AND mh_active_game_details.`userId` = "
						+ saveAnswerDetail.getuId() + "");
				batchSql[3] = "COMMIT";
				jdbcService.getJdbcTemplate().batchUpdate(batchSql);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public UserLoginRequest verifyOtp(UserLoginRequest userLoginRequest) {

		UserLoginRequest userInfo = jdbcService.getJdbcTemplate()
				.query("SELECT `user_id` FROM `mh_user_access_details` WHERE username = '"
						+ userLoginRequest.getMobileNo() + "'", new UserDetailResultExtracter());

		return userInfo;
	}

	public List<PlayerDataDetail> getPlayersDetail(PlayerDetail playerDetail) {
		List<PlayerDataDetail> lstPlayerDataDetail = new ArrayList<>();
		PlayerDataDetail playerDataDetail = new PlayerDataDetail();
		lstPlayerDataDetail = jdbcService.getJdbcTemplate().query(
				"SELECT mh_active_game_details.userRank ,mh_user_access_details.username FROM mh_active_game_details LEFT JOIN mh_user_access_details ON mh_active_game_details.userId = mh_user_access_details.user_id WHERE mh_active_game_details.gameId = "
						+ playerDetail.getGameId() + " AND mh_active_game_details.packId = " + playerDetail.getPackId()
						+ " AND mh_active_game_details.instanceId = " + playerDetail.getInstanceId()
						+ " ORDER BY mh_active_game_details.userRank",
				new PlayerDataRowMapper());

		// TODO Auto-generated method stub
		return lstPlayerDataDetail;
	}
	  public String endMegaSharkOperation(PlayerDetail gamePlayRequest)
	  {
	    int update = jdbcService.getJdbcTemplate().update("INSERT INTO mh_user_reward_details(user_id, reward_id, game_id,pack_id,win_date) SELECT `userId`, `score`, `gameId`,`packId`,`updatedDate` FROM `mh_active_game_details`  WHERE mh_active_game_details.`gameId` = " + gamePlayRequest.getGameId() + " AND mh_active_game_details.`packId` = " + gamePlayRequest.getPackId() + " AND mh_active_game_details.`userId` = " + gamePlayRequest.getUserId() + " ");
	    if (update > 0) {
	      String[] batchSql = new String[4];
	      batchSql[0] = "BEGIN";
	      batchSql[1] = ("INSERT INTO `mh_game_player_detail_history` SELECT * FROM `mh_active_game_details`  WHERE mh_active_game_details.`gameId` = " + gamePlayRequest.getGameId() + " AND mh_active_game_details.`packId` = " + gamePlayRequest.getPackId() + " AND mh_active_game_details.`userId` = " + gamePlayRequest.getUserId() + "");
	      batchSql[2] = ("DELETE FROM `mh_active_game_details` WHERE mh_active_game_details.`gameId` = " + gamePlayRequest.getGameId() + " AND mh_active_game_details.`packId` = " + gamePlayRequest.getPackId() + " AND mh_active_game_details.`userId` = " + gamePlayRequest.getUserId() + "");
	      batchSql[3] = "COMMIT";
	      jdbcService.getJdbcTemplate().batchUpdate(batchSql);
	    }
	    
	    return update > 0 ? "success" : null;
	  }

	public String claimPrize(PlayerDetail playerDetail) {

		int update = jdbcService.getJdbcTemplate().update(
				"INSERT INTO mh_user_reward_details(user_id, reward_id, game_id,pack_id,win_date,score,rewardValue,rewardDetailUnit) SELECT `userId`, `instanceId`, `gameId`,`packId`,`updatedDate`,correctAnswerCount,'"
						+ playerDetail.getRewardValue() + "', '" + playerDetail.getDenominationType()
						+ "' FROM `mh_active_game_details`  WHERE mh_active_game_details.`gameId` = "
						+ playerDetail.getGameId() + " AND mh_active_game_details.`packId` = "
						+ playerDetail.getPackId() + " AND mh_active_game_details.`instanceId` = "
						+ playerDetail.getInstanceId() + "   and  mh_active_game_details.userRank = (1 or 0) ");
		// jdbcService.getJdbcTemplate().update("update mh_user_reward_details set
		// rewardValue = "+playerDetail.getRewardValue()+" , rewardDetailUnit =
		// "+playerDetail.getDenominationType()+" whsre game_id =
		// "+playerDetail.getGameId()+" ,pack_id ="+playerDetail.getPackId()+",reward_id
		// ="+playerDetail.getInstanceId()+" ");

		if (update > 0) {
			String batchSql[] = new String[4];
			batchSql[0] = "BEGIN";
			batchSql[1] = ("INSERT INTO `mh_game_player_detail_history` SELECT * FROM `mh_active_game_details`  WHERE mh_active_game_details.`gameId` = "
					+ playerDetail.getGameId() + " AND mh_active_game_details.`packId` = " + playerDetail.getPackId()
					+ " AND mh_active_game_details.`instanceId` = " + playerDetail.getInstanceId() + "");
			batchSql[2] = ("DELETE FROM `mh_active_game_details` WHERE mh_active_game_details.`gameId` = "
					+ playerDetail.getGameId() + " AND mh_active_game_details.`packId` = " + playerDetail.getPackId()
					+ " AND mh_active_game_details.`instanceId` = " + playerDetail.getInstanceId() + "");
			batchSql[3] = "COMMIT";
			jdbcService.getJdbcTemplate().batchUpdate(batchSql);
			return "succeess";
		} else {

			return null;
		}
	}

	public String timeTaken(SaveAnswerDetail reqUserAnswerTimeSpend, int answerValue) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		int update = jdbcService.getJdbcTemplate().update(
				"INSERT INTO mh_user_answer_timings (userId,gameId,packId,instanceId,timeSpend,created_time,qId,answerStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[] { reqUserAnswerTimeSpend.getuId(), reqUserAnswerTimeSpend.getGameId(),
						reqUserAnswerTimeSpend.getPackId(), reqUserAnswerTimeSpend.getGameInstanceId(),
						reqUserAnswerTimeSpend.getSpentTime(), timestamp, reqUserAnswerTimeSpend.getqId(),
						answerValue });
		return update != 0 ? "success" : "fail";
	}

	public void savetransactionDetail(PaytmRequest paytmRequest) {
		int update = jdbcService.getJdbcTemplate().update(
				"INSERT INTO transaction_details(userId,packId,instanceId,gameId,transactionTime,order_id, transaction_type) VALUES (?,?,?,?,?,?,?)",
				new Object[] { paytmRequest.getUserId(), paytmRequest.getPackId(), paytmRequest.getInstanseId(),
						paytmRequest.getGameId(), (new Timestamp(System.currentTimeMillis())).toString(),
						paytmRequest.getOrderId(), 1 });

	}

	/*
	 * CURRENCY=INR, GATEWAYNAME=WALLET, RESPMSG=Txn+Success, BANKNAME=WALLET,
	 * PAYMENTMODE=PPI, MID=MINDHU40541790566351, RESPCODE=01,
	 * TXNID=20190205111212800110168736400227356, TXNAMOUNT=20.00,
	 * ORDERID=1920190205140229600, STATUS=TXN_SUCCESS, BANKTXNID=6290132,
	 * TXNDATE=2019-02-05+19%3A32%3A32.0,
	 * CHECKSUMHASH=Apbrd1TX9oH1NTGvv3sTU%2FISZAMkB%2FjCltAjhr9aerZu8t9%2F7wv9o6L7%
	 * 2BOHv%2Bu85FI0qqAQT%2FVXmk678xzvHPCWKG3dQ5Qo2MvwgrZAFe70%3D}
	 */

	public void updateTransactionDetail(Map<String, String> paytmResponse) {
		int update = jdbcService.getJdbcTemplate().update(
				"UPDATE transaction_details  SET callback_time = ?,txn_id = ?,payment_mode = ?,currency = ? ,paytm_txn_date_time = ?,STATUS = ? ,response_code = ? ,response_msg = ? ,getway_name = ?,bank_txn_id = ? ,bank_name = ? ,checksumHash = ? WHERE order_id = ?",
				new Object[] { (new Timestamp(System.currentTimeMillis())).toString(), paytmResponse.get("TXNID"),
						paytmResponse.get("PAYMENTMODE"), paytmResponse.get("CURRENCY"), paytmResponse.get("TXNDATE"),
						paytmResponse.get("STATUS"), paytmResponse.get("RESPCODE"), paytmResponse.get("RESPMSG"),
						paytmResponse.get("GATEWAYNAME"), paytmResponse.get("BANKTXNID"), paytmResponse.get("BANKNAME"),
						paytmResponse.get("CHECKSUMHASH"), paytmResponse.get("ORDERID") });

	}

	public String claimPrizeForQuickWin(PlayerDetail playerDetail) {
		String createDate = jdbcService.getJdbcTemplate()
				.query("select createdDate as noOfusers from mh_active_game_details where gameId ="
						+ playerDetail.getGameId() + " and userId = " + playerDetail.getUserId() + "",
						new StringValueResultExtracter());
		System.out.println("instanse value " + createDate);
		int update = 0;
		if (createDate != null) {
			String instanseValue = createDate.replaceAll("[-,:,.]", "");
			String newInstanseValue = instanseValue.replaceAll(" ", "");
			update = jdbcService.getJdbcTemplate().update(
					"INSERT INTO snap_win_winners_details(userId, gameid, packid,instanseId,windate)  VALUES (?,?,?,?,?)",
					new Object[] { playerDetail.getUserId(), playerDetail.getGameId(), playerDetail.getPackId(),
							newInstanseValue, (new Timestamp(System.currentTimeMillis())).toString() });
		}
		String batchSql[] = new String[4];

		batchSql[0] = "BEGIN";
		batchSql[1] = ("INSERT INTO `mh_game_player_detail_history` SELECT * FROM `mh_active_game_details`  WHERE mh_active_game_details.`gameId` = "
				+ playerDetail.getGameId() + " AND mh_active_game_details.`packId` = " + playerDetail.getPackId()
				+ " and  userId = " + playerDetail.getUserId() + " ");
		batchSql[2] = ("DELETE FROM `mh_active_game_details` WHERE mh_active_game_details.`gameId` = "
				+ playerDetail.getGameId() + " AND mh_active_game_details.`packId` = " + playerDetail.getPackId()
				+ " and  userId = " + playerDetail.getUserId() + "");
		batchSql[3] = "COMMIT";
		jdbcService.getJdbcTemplate().batchUpdate(batchSql);

		return update != 0 ? "success" : "fail";
	}

	public String claimPrizeForPoolPrize(PlayerDetail playerDetail) {
		String createDate = jdbcService.getJdbcTemplate()
				.query("select createdDate as noOfusers from mh_active_game_details where gameId ="
						+ playerDetail.getGameId() + " and userId = " + playerDetail.getUserId() + "",
						new StringValueResultExtracter());
		System.out.println("instanse value " + createDate);
		int update = 0;
		if (createDate != null) {
			String instanseValue = createDate.replaceAll("[-,:,.]", "");
			String newInstanseValue = instanseValue.replaceAll(" ", "");
			update = jdbcService.getJdbcTemplate().update(
					"INSERT INTO pool_prize_winners_details(userId, gameid, packid,instanseId,total_correct,windate,tier_value)  VALUES (?,?,?,?,?,?,?)",
					new Object[] { playerDetail.getUserId(), playerDetail.getGameId(), playerDetail.getPackId(),
							newInstanseValue, playerDetail.getTotalCorrectAnswer(),
							(new Timestamp(System.currentTimeMillis())).toString(), playerDetail.getTierValue() });
		}
		String batchSql[] = new String[4];

		batchSql[0] = "BEGIN";
		batchSql[1] = ("INSERT INTO `mh_game_player_detail_history` SELECT * FROM `mh_active_game_details`  WHERE mh_active_game_details.`gameId` = "
				+ playerDetail.getGameId() + " AND mh_active_game_details.`packId` = " + playerDetail.getPackId()
				+ " and  userId = " + playerDetail.getUserId() + "");
		batchSql[2] = ("DELETE FROM `mh_active_game_details` WHERE mh_active_game_details.`gameId` = "
				+ playerDetail.getGameId() + " AND mh_active_game_details.`packId` = " + playerDetail.getPackId()
				+ " and  userId = " + playerDetail.getUserId() + "");
		batchSql[3] = "COMMIT";
		jdbcService.getJdbcTemplate().batchUpdate(batchSql);

		return update != 0 ? "success" : "fail";
	}

	public void updateimage() {
		List<Qimage> s = jdbcService.getJdbcTemplate().query("select qId,qImage from api_question_details",
				new TempRowMapper());
		for (Qimage n : s) {
			System.out.println(n.getImage());
			String p = n.getImage().substring(37);
			System.out.println(p);
			jdbcService.getJdbcTemplate()
					.update("update api_question_details set qImage = '" + p + "' where qId = " + n.getId() + " ");

		}
	}

	public String addSupportQuery(SupportQueryDetails supportQueryDetails) {
		String sql = "INSERT INTO mh_support_queries(name,mobile_number,email,contest_name,query,date_time,UserMobileNumber)  VALUES(?,?,?,?,?,?,?)";
		int update = jdbcService.getJdbcTemplate().update(sql,
				new Object[] { supportQueryDetails.getName(), supportQueryDetails.getMobileNo(),
						supportQueryDetails.getEmail(), supportQueryDetails.getContestName(),
						supportQueryDetails.getQuery(), (new Timestamp(System.currentTimeMillis())).toString(),
						Integer.valueOf(supportQueryDetails.getUserId()) });
		return update == 0 ? null : "sucess";

	}

	public Object getnewPackListData(Object fromJson) {
		List<PackListDetail> lstPackListDetail = new ArrayList();

		List<Map> list = (ArrayList) fromJson;
		ObjectMapper mapper = new ObjectMapper();
		for (Map packList : list) {
			String onlinePlayer = (String) jdbcService.getJdbcTemplate()
					.query("SELECT COUNT(`userId`) FROM `mh_active_game_details` WHERE `packId` = "
							+ packList.get("packId") + "", new StringValueResultExtracter());

			PackListDetail anotherFoo = (PackListDetail) mapper.convertValue(packList, PackListDetail.class);
			anotherFoo.setOnlinePlayer(onlinePlayer);
			lstPackListDetail.add(anotherFoo);
		}
		return lstPackListDetail;
	}

	public Object getActivePlayerDetail(GamePlayRequest gpr) {
		List<ActivePlayerDetails> lstActivePlayerDetails = new ArrayList();
		String query = null;
		 query = "SELECT `packId`, `totalQuestion`-(`correctAnswerCount`+`wrongAnswerCount`) AS `remainingQuestion` , `totalQuestion` ,score  FROM `mh_active_game_details` WHERE `userId` = "
				+ gpr.getUserId() + " AND `gameId` =" + gpr.getGameId() + " ";
		if ((gpr.getGameId() != null) && (gpr.getGameId().equalsIgnoreCase("3")))
		query = "SELECT  mh_active_game_details.`packId`, mh_active_game_details.`totalQuestion`-(mh_active_game_details.`correctAnswerCount`+mh_active_game_details.`wrongAnswerCount`) AS `remainingQuestion` , mh_active_game_details.`totalQuestion` ,mh_active_game_details.correctAnswerCount AS score , mh_game_today_score.score as todayScore   FROM `mh_active_game_details`  LEFT JOIN mh_game_today_score ON mh_active_game_details.packId = mh_game_today_score.packId WHERE mh_active_game_details.`userId` = "+gpr.getUserId()+" AND mh_active_game_details.`gameId` = 3 and mh_game_today_score.scoreDate = '"+java.time.LocalDate.now()+"'";
		System.out.println("query ==>" + query);
		if ((gpr.getGameId() != null) && (gpr.getGameId().equalsIgnoreCase("4"))) {
			query = "SELECT `packId`, `totalQuestion`-(`correctAnswerCount`+`wrongAnswerCount`) AS `remainingQuestion` , `totalQuestion` ,correctAnswerCount as score  FROM `mh_active_game_details` WHERE `userId` = "
					+ gpr.getUserId() + " AND `gameId` =" + gpr.getGameId() + " ";
		}
		lstActivePlayerDetails = jdbcService.getJdbcTemplate().query(query, new ActivePlayerDetailsRowMapper());
		return lstActivePlayerDetails.isEmpty() ? null : lstActivePlayerDetails;
	}

	public Object saveActivePlayerDetail(GamePlayRequest gpr) {
		int update = 0;
		String sql = "INSERT INTO mh_active_game_details(userId,gameId,packId,totalQuestion,createdDate)  VALUES(?,?,?,?,?)";

		String checkSql = "SELECT `packId` FROM  `mh_active_game_details` WHERE `userId` =" + gpr.getUserId()
				+ " AND `gameId` = " + gpr.getGameId() + " AND `packId` =" + gpr.getPackId() + " ";
		String packValue = (String) jdbcService.getJdbcTemplate().query(checkSql, new StringValueResultExtracter());
		if (packValue == null) {
			update = jdbcService.getJdbcTemplate().update(sql,
					new Object[] { Integer.valueOf(gpr.getUserId()), gpr.getGameId(), gpr.getPackId(),
							gpr.getTotalQuestions(), new Timestamp(System.currentTimeMillis()).toString() });
		}

		return update != 0 ? Integer.valueOf(update) : null;

	}

	public int userLeft(int userId, int instanceId) {
		int update = 0;
		int counts = 0;
		String userStatus = "UPDATE mh_active_game_details SET status = 1, updatedDate = ? WHERE userId = ? AND instanceId = ? AND status = 0 ";
		String usersCount = "UPDATE mh_game_instance SET noOfusers = noOfusers-1 WHERE id = ? AND noOfusers > 0";
		try {
			int status = jdbcService.getJdbcTemplate().update(userStatus, new Object[] {
					new Timestamp(System.currentTimeMillis()), Integer.valueOf(userId), Integer.valueOf(instanceId) });
			if (status > 0) {
				counts = jdbcService.getJdbcTemplate().update(usersCount, new Object[] { Integer.valueOf(instanceId) });
			}

			if ((status > 0) && (counts > 0)) {
				update = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return update;
	}

	public int updateInstanceStatus(int instanceId) {
		int updateStatus = 0;
		String query = "UPDATE mh_game_instance SET status = 4 WHERE id = ?";
		try {
			updateStatus = jdbcService.getJdbcTemplate().update(query, new Object[] { Integer.valueOf(instanceId) });
		} catch (Exception e) {
			e.printStackTrace();
		}

		return updateStatus;
	}

	public GameInstanceResponse getInstance(int userId, int gameId, int packId) {
		GameInstanceResponse res = new GameInstanceResponse();
		System.out.println("getInstance method");
		int instId = 0;

		try {
			instId = checkInstance(gameId, userId, packId);
			if (instId > 0) {
				int update = insertGameInstanceUsers(gameId, userId, packId, instId);
				res.setStatusMessage("Instance Succesfully Added");
				res.setStatus("Success");
				res.setStatusCode("0");
				res.setInstanceId(instId);
			}
			System.out.println("request   Completed");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	private int checkInstance(int gameId, int userId, int packId) {
		int instId = 0;

		String query = "SELECT id,noOfusers,status FROM mh_game_instance WHERE gameId = ? AND packId = ? ORDER BY id DESC LIMIT 1";
		try {
			System.out.println("check Instance");
			List<GameInstance> statusList = jdbcService.getJdbcTemplate().query(query, new InstanceMapper(),
					new Object[] { Integer.valueOf(gameId), Integer.valueOf(packId) });
			System.out.println("Instance statusList Size===> " + statusList.size());
			if (statusList.size() == 0) {
				instId = insertGameInstance(gameId, packId);
			} else {
				for (GameInstance list : statusList) {
					System.out.println(
							"No Of users Exist ==> " + list.getNoOfUsers() + "   Status==> " + list.getStatus());
					if (list.getNoOfUsers() == 5) {
						instId = insertGameInstance(gameId, packId);
					} else {
						int noOfUsers = list.getNoOfUsers();
						instId = list.getId();
						noOfUsers++;
						updategameInstance(noOfUsers, gameId, packId);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return instId;

	}

	private int updategameInstance(int noOfusers, int gameId, int packId) {
		String query = "UPDATE mh_game_instance SET noOfusers = ? WHERE gameId = ? AND packId = ?";
		int update = 0;
		System.out.println("ready to update exist instance with nof Of users==> " + noOfusers);
		try {
			update = jdbcService.getJdbcTemplate().update(query,
					new Object[] { Integer.valueOf(noOfusers), Integer.valueOf(gameId), Integer.valueOf(packId) });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return update;
	}

	private int checkUser(int gameId, int userId, int packId) {
		int instancId = 0;
		String checkStatus = "SELECT userId,instanceId FROM mh_active_game_details WHERE instanceId IN (SELECT id FROM mh_game_instance WHERE gameId = ? AND packId = ? AND STATUS = 0)";
		List<GameInstanceUsers> usersInGame = jdbcService.getJdbcTemplate().query(checkStatus, new InstUsersMapper(),
				new Object[] { Integer.valueOf(gameId), Integer.valueOf(packId) });
		if (usersInGame.size() > 0) {
			for (GameInstanceUsers list : usersInGame) {
				if (list.getUserId() == userId) {
					System.out.println("user Exist");
					instancId = list.getInstanceId();
				}
			}
		} else {
			instancId = 0;
		}
		return instancId;
	}

	private int insertGameInstanceUsers(int gameId, int userId, int packId, int instanceId) {
		int availableLifeLine = 3;
		int status = 0;
		System.out.println("ready to insert in gameinst users");
		String query = "INSERT INTO mh_active_game_details(gameId,userId,packId,instanceId,availableLifeLine,status,createdDate,updatedDate) VALUES(?,?,?,?,?,?,?,?)";
		int update = 0;
		try {
			update = jdbcService.getJdbcTemplate().update(query,
					new Object[] { Integer.valueOf(gameId), Integer.valueOf(userId), Integer.valueOf(packId),
							Integer.valueOf(instanceId), Integer.valueOf(availableLifeLine), Integer.valueOf(status),
							new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()) });
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("inserted in gameinst users");
		return update;
	}

	private int insertGameInstance(int gameId, int packId)
		  {
        String query = "INSERT INTO mh_game_instance (gameId,noOfusers,packId,status,createdDate) VALUES (?,?,?,?,?)";
        int instanceId = 0;
        System.out.println("Ready To create New Instance ");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            int update = jdbcService.getJdbcTemplate().update(new PreparedStatementCreator() {
				@Override
				public java.sql.PreparedStatement createPreparedStatement(java.sql.Connection connection) throws SQLException {
					 PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query, new String[]{"id"});
	                    ps.setInt(1, gameId);
	                    ps.setInt(2, 1);
	                    ps.setInt(3, packId);
	                    ps.setInt(4, 0);
	                    ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
	                    return ps;
				}
            }, keyHolder);
            instanceId = keyHolder.getKey().intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("new instance Created  == > " + instanceId);
        return instanceId;

}
}