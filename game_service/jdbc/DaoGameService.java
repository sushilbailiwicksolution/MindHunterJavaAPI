package com.bailiwick.game_service.jdbc;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bailiwick.game_service.resultExtracter.SingleIntegerValueExtracter;
import com.bailiwick.game_service.resultExtracter.StringValueResultExtracter;
import com.bailiwick.game_service.resultExtracter.UserDetailResultExtracter;
import com.bailiwick.game_service.util.Queries;
import com.bailiwick.game_servicei.mapper.PlayerDataRowMapper;
import com.bailiwick.game_servicei.mapper.WinnerDetailRowMapper;
import com.bailiwick.game_servicei.model.PaytmRequest;
import com.bailiwick.game_servicei.model.PlayerDataDetail;
import com.bailiwick.game_servicei.model.PlayerDetail;
import com.bailiwick.game_servicei.model.PriceRequest;
import com.bailiwick.game_servicei.model.SaveAnswerDetail;
import com.bailiwick.game_servicei.model.UserLoginRequest;
import com.bailiwick.game_servicei.model.WinnerDetail;
import com.bailiwick.game_servicei.model.WinnerResponse;

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
	
	        
	        
	    query = " UPDATE mh_active_game_details SET mh_active_game_details.correctAnswerCount = correctAnswerCount + 1,updatedDate = '"+(new Timestamp(System.currentTimeMillis())).toString()+"'   WHERE `userId` = "+saveAnswerDetail.getuId()+" AND `gameId` ="+saveAnswerDetail.getGameId()+" AND `instanceId` = "+saveAnswerDetail.getGameInstanceId()+" AND `packId` = "+saveAnswerDetail.getPackId()+"";
	        try
	        {
	            System.out.println((new StringBuilder()).append("increase answer query ==>").append(query).toString());
	            jdbcService.getJdbcTemplate().update(query);
	            timeTaken(saveAnswerDetail,1);
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	    

	}

	public void increseWrongAnswerCount(SaveAnswerDetail saveAnswerDetail) {
	    String query = null;
	    query = " UPDATE mh_active_game_details SET mh_active_game_details.wrongAnswerCount = wrongAnswerCount  + 1,updatedDate = '"+(new Timestamp(System.currentTimeMillis())).toString()+"'   WHERE `userId` = "+saveAnswerDetail.getuId()+" AND `gameId` ="+saveAnswerDetail.getGameId()+" AND `instanceId` = "+saveAnswerDetail.getGameInstanceId()+" AND `packId` = "+saveAnswerDetail.getPackId()+"";
	        try
	        {
	            System.out.println((new StringBuilder()).append("increase answer query ==>").append(query).toString());
	            jdbcService.getJdbcTemplate().update(query);
	            timeTaken(saveAnswerDetail,2);
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	    
	}
	public UserLoginRequest verifyOtp(UserLoginRequest userLoginRequest) {

		UserLoginRequest userInfo = jdbcService.getJdbcTemplate()
				.query("SELECT `user_id` FROM `mh_user_access_details` WHERE username = '"
						+ userLoginRequest.getMobileNo() + "'",
						new UserDetailResultExtracter());

		return userInfo;
	}

	public List<PlayerDataDetail> getPlayersDetail(PlayerDetail playerDetail) {
		List<PlayerDataDetail> lstPlayerDataDetail = new ArrayList<>();
		PlayerDataDetail playerDataDetail = new PlayerDataDetail();
		lstPlayerDataDetail = jdbcService.getJdbcTemplate().query("SELECT mh_active_game_details.userRank ,mh_user_access_details.username FROM mh_active_game_details LEFT JOIN mh_user_access_details ON mh_active_game_details.userId = mh_user_access_details.user_id WHERE mh_active_game_details.gameId = "+playerDetail.getGameId()+" AND mh_active_game_details.packId = "+playerDetail.getPackId()+" AND mh_active_game_details.instanceId = "+playerDetail.getInstanceId()+" ORDER BY mh_active_game_details.userRank" ,new PlayerDataRowMapper());
		
		// TODO Auto-generated method stub
		return lstPlayerDataDetail;
	}

	public String claimPrize(PlayerDetail playerDetail) {
   
		
		int update = jdbcService.getJdbcTemplate().update("INSERT INTO mh_user_reward_details(user_id, reward_id, game_id,pack_id,win_date,score,rewardValue,rewardDetailUnit) SELECT `userId`, `instanceId`, `gameId`,`packId`,`updatedDate`,correctAnswerCount,'"+playerDetail.getRewardValue()+"', '"+playerDetail.getDenominationType()+"' FROM `mh_active_game_details`  WHERE mh_active_game_details.`gameId` = "+playerDetail.getGameId()+" AND mh_active_game_details.`packId` = "+playerDetail.getPackId()+" AND mh_active_game_details.`instanceId` = "+playerDetail.getInstanceId()+"   and  mh_active_game_details.userRank = (1 or 0) ");
		//jdbcService.getJdbcTemplate().update("update mh_user_reward_details set  rewardValue = "+playerDetail.getRewardValue()+" , rewardDetailUnit = "+playerDetail.getDenominationType()+" whsre game_id = "+playerDetail.getGameId()+" ,pack_id ="+playerDetail.getPackId()+",reward_id ="+playerDetail.getInstanceId()+"  ");
		
		if(update > 0)
        {
            String batchSql[] = new String[4];
            batchSql[0] = "BEGIN";
            batchSql[1] = ("INSERT INTO `mh_game_player_detail_history` SELECT * FROM `mh_active_game_details`  WHERE mh_active_game_details.`gameId` = "+playerDetail.getGameId()+" AND mh_active_game_details.`packId` = "+playerDetail.getPackId()+" AND mh_active_game_details.`instanceId` = "+playerDetail.getInstanceId()+"");
            batchSql[2] = ("DELETE FROM `mh_active_game_details` WHERE mh_active_game_details.`gameId` = "+playerDetail.getGameId()+" AND mh_active_game_details.`packId` = "+playerDetail.getPackId()+" AND mh_active_game_details.`instanceId` = "+playerDetail.getInstanceId()+"");
            batchSql[3] = "COMMIT";
            jdbcService.getJdbcTemplate().batchUpdate(batchSql);
            return "succeess";
        }else {

		return null;
        }
	}

	public String timeTaken(SaveAnswerDetail reqUserAnswerTimeSpend , int answerValue) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());		
	int update = jdbcService.getJdbcTemplate().update("INSERT INTO mh_user_answer_timings (userId,gameId,packId,instanceId,timeSpend,created_time,qId,answerStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", new Object[]{reqUserAnswerTimeSpend.getuId(),reqUserAnswerTimeSpend.getGameId(),reqUserAnswerTimeSpend.getPackId(),reqUserAnswerTimeSpend.getGameInstanceId(),reqUserAnswerTimeSpend.getSpentTime(),timestamp,reqUserAnswerTimeSpend.getqId(),answerValue});
		return update!=0?"success":"fail";
}

	public void savetransactionDetail(PaytmRequest paytmRequest) {
		int update = jdbcService.getJdbcTemplate().update("INSERT INTO transaction_details(userId,packId,instanceId,gameId,transactionTime,order_id, transaction_type) VALUES (?,?,?,?,?,?,?)", new Object[]{paytmRequest.getUserId(),paytmRequest.getPackId(),paytmRequest.getInstanseId(),paytmRequest.getGameId(),(new Timestamp(System.currentTimeMillis())).toString(),paytmRequest.getOrderId(),1});
		
	}
	
	/*CURRENCY=INR, 
			GATEWAYNAME=WALLET, 
			RESPMSG=Txn+Success, 
			BANKNAME=WALLET, 
			PAYMENTMODE=PPI, 
			MID=MINDHU40541790566351, 
			RESPCODE=01, 
			TXNID=20190205111212800110168736400227356, 
			TXNAMOUNT=20.00, ORDERID=1920190205140229600, 
			STATUS=TXN_SUCCESS, BANKTXNID=6290132, 
			TXNDATE=2019-02-05+19%3A32%3A32.0, 
			CHECKSUMHASH=Apbrd1TX9oH1NTGvv3sTU%2FISZAMkB%2FjCltAjhr9aerZu8t9%2F7wv9o6L7%2BOHv%2Bu85FI0qqAQT%2FVXmk678xzvHPCWKG3dQ5Qo2MvwgrZAFe70%3D}
*/

	public void updateTransactionDetail(Map<String, String> paytmResponse) {
	int update = jdbcService.getJdbcTemplate().update("UPDATE transaction_details  SET callback_time = ?,txn_id = ?,payment_mode = ?,currency = ? ,paytm_txn_date_time = ?,STATUS = ? ,response_code = ? ,response_msg = ? ,getway_name = ?,bank_txn_id = ? ,bank_name = ? ,checksumHash = ? WHERE order_id = ?", new Object[]{(new Timestamp(System.currentTimeMillis())).toString(),paytmResponse.get("TXNID"),paytmResponse.get("PAYMENTMODE"),paytmResponse.get("CURRENCY"),paytmResponse.get("TXNDATE"),paytmResponse.get("STATUS"),paytmResponse.get("RESPCODE"),paytmResponse.get("RESPMSG"),paytmResponse.get("GATEWAYNAME"),paytmResponse.get("BANKTXNID"),paytmResponse.get("BANKNAME"),paytmResponse.get("CHECKSUMHASH"),paytmResponse.get("ORDERID")});
		
	}

	public String claimPrizeForQuickWin(PlayerDetail playerDetail) {
		String createDate = jdbcService.getJdbcTemplate().query("select createdDate as noOfusers from mh_active_game_details where gameId ="+playerDetail.getGameId()+" and userId = "+playerDetail.getUserId()+"", new StringValueResultExtracter ());
		System.out.println("instanse value " + createDate);
		int update = 0;
		if (createDate!=null) {
		String instanseValue = createDate.replaceAll("[-,:,.]", "");
		String newInstanseValue = instanseValue.replaceAll(" ", "");
		update  = jdbcService.getJdbcTemplate().update("INSERT INTO snap_win_winners_details(userId, gameid, packid,instanseId,windate)  VALUES (?,?,?,?,?)" , new Object[]{playerDetail.getUserId(),playerDetail.getGameId(),playerDetail.getPackId(),newInstanseValue,(new Timestamp(System.currentTimeMillis())).toString()});
}
		String batchSql[] = new String[4];
       
       batchSql[0] = "BEGIN";
       batchSql[1] = ("INSERT INTO `mh_game_player_detail_history` SELECT * FROM `mh_active_game_details`  WHERE mh_active_game_details.`gameId` = "+playerDetail.getGameId()+" AND mh_active_game_details.`packId` = "+playerDetail.getPackId()+" and  userId = "+playerDetail.getUserId()+" ");
       batchSql[2] = ("DELETE FROM `mh_active_game_details` WHERE mh_active_game_details.`gameId` = "+playerDetail.getGameId()+" AND mh_active_game_details.`packId` = "+playerDetail.getPackId()+" and  userId = "+playerDetail.getUserId()+"");
       batchSql[3] = "COMMIT";
        jdbcService.getJdbcTemplate().batchUpdate(batchSql);
      
		return update!=0?"success":"fail";
	}

	public String claimPrizeForPoolPrize(PlayerDetail playerDetail) {
		String createDate = jdbcService.getJdbcTemplate().query("select createdDate as noOfusers from mh_active_game_details where gameId ="+playerDetail.getGameId()+" and userId = "+playerDetail.getUserId()+"", new StringValueResultExtracter ());
		System.out.println("instanse value " + createDate);
		int update = 0;
		if (createDate!=null) {
		String instanseValue = createDate.replaceAll("[-,:,.]", "");
		String newInstanseValue = instanseValue.replaceAll(" ", "");
		update  = jdbcService.getJdbcTemplate().update("INSERT INTO pool_prize_winners_details(userId, gameid, packid,instanseId,total_correct,windate,tier_value)  VALUES (?,?,?,?,?,?,?)" , new Object[]{playerDetail.getUserId(),playerDetail.getGameId(),playerDetail.getPackId(),newInstanseValue,playerDetail.getTotalCorrectAnswer(),(new Timestamp(System.currentTimeMillis())).toString(), playerDetail.getTierValue()});
}
		String batchSql[] = new String[4];
       
       batchSql[0] = "BEGIN";
       batchSql[1] = ("INSERT INTO `mh_game_player_detail_history` SELECT * FROM `mh_active_game_details`  WHERE mh_active_game_details.`gameId` = "+playerDetail.getGameId()+" AND mh_active_game_details.`packId` = "+playerDetail.getPackId()+" and  userId = "+playerDetail.getUserId()+"");
       batchSql[2] = ("DELETE FROM `mh_active_game_details` WHERE mh_active_game_details.`gameId` = "+playerDetail.getGameId()+" AND mh_active_game_details.`packId` = "+playerDetail.getPackId()+" and  userId = "+playerDetail.getUserId()+"");
       batchSql[3] = "COMMIT";
        jdbcService.getJdbcTemplate().batchUpdate(batchSql);
      
		return update!=0?"success":"fail";}

	
}