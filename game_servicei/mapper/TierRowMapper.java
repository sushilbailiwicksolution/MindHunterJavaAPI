package com.bailiwick.game_servicei.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.bailiwick.game_servicei.model.ResTiersDetails;

public class TierRowMapper implements RowMapper<ResTiersDetails>{

	private Logger logger = (Logger) LogManager.getLogger(getClass());
	
	@Override
	public ResTiersDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResTiersDetails tiersDetails = null;
		
		try {
			tiersDetails = new ResTiersDetails();
			
			tiersDetails.setNoOfQuestion(Integer.parseInt(rs.getString("no_of_question")));
			tiersDetails.setReward(Integer.parseInt(rs.getString("reward")));
			
			return tiersDetails;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
