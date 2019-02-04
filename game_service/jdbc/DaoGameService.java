package com.bailiwick.game_service.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bailiwick.game_service.resultExtracter.SingleIntegerValueExtracter;
import com.bailiwick.game_service.resultExtracter.UserDetailResultExtracter;
import com.bailiwick.game_service.util.Queries;
import com.bailiwick.game_servicei.mapper.PlayerDataRowMapper;
import com.bailiwick.game_servicei.mapper.WinnerDetailRowMapper;
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

		// TODO Auto-generated method stub

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
        int update = jdbcService.getJdbcTemplate().update("INSERT INTO mh_user_reward_details(user_id, reward_id, game_id,pack_id,win_date) SELECT `userId`, `instanceId`, `gameId`,`packId`,`updatedDate` FROM `mh_active_game_details`  WHERE mh_active_game_details.`gameId` = "+playerDetail.getGameId()+" AND mh_active_game_details.`packId` = "+playerDetail.getPackId()+" AND mh_active_game_details.`instanceId` = "+playerDetail.getInstanceId()+"   and  mh_active_game_details.userRank = 1");
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

}
