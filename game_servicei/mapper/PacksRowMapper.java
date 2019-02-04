package com.bailiwick.game_servicei.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.bailiwick.game_servicei.model.ResPacksDetails;

public class PacksRowMapper implements RowMapper<ResPacksDetails>{

	private Logger logger = (Logger) LogManager.getLogger(getClass());
	
	@Override
	public ResPacksDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResPacksDetails packsDetails = null;
		
		try {
			packsDetails = new ResPacksDetails();
			
			packsDetails.setDescription(rs.getString("description"));
			packsDetails.setEntryFee(rs.getInt("entry_fee"));
			packsDetails.setGameId(rs.getInt("gameId"));
			packsDetails.setLifeLines(rs.getInt("lifeLines"));
			packsDetails.setLoserMsg(rs.getString("loser_msg"));
			packsDetails.setMaxPlayer(rs.getInt("max_player"));
			packsDetails.setMaxPlayTime(rs.getInt("max_play_time"));
			packsDetails.setMinPlayer(rs.getInt("min_player"));
			packsDetails.setPackId(rs.getInt("packId"));
			packsDetails.setPackTitle(rs.getString("packId"));
			packsDetails.setQlevel(rs.getInt("qlevel"));
			packsDetails.setReward(rs.getInt("reward"));
			packsDetails.settAndC(rs.getString("tAndC"));
			packsDetails.setTimePerQ(rs.getInt("time_per_q"));
			packsDetails.setTotalQ(rs.getInt("totalQ"));
			packsDetails.setWinnerMsg(rs.getString("winner_msg"));
			packsDetails.setPackType(rs.getInt("pack_type"));
			packsDetails.setPointsPerQuestion(rs.getInt("points_per_Q"));
			packsDetails.setSeason(rs.getString("season"));
			
			return packsDetails;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
