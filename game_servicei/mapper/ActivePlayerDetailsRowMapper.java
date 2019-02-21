package com.bailiwick.game_servicei.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.bailiwick.game_servicei.model.ActivePlayerDetails;

public class ActivePlayerDetailsRowMapper implements RowMapper<ActivePlayerDetails> {
	private Logger logger = (Logger) LogManager.getLogger(getClass());

	@Override
	public ActivePlayerDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		 ActivePlayerDetails activePlayerDetails = new ActivePlayerDetails();

		    activePlayerDetails.setPackId(rs.getString("packId"));
		    activePlayerDetails.setRemainingQuestion(rs.getString("remainingQuestion"));
		    activePlayerDetails.setScore(rs.getString("score"));
		    activePlayerDetails.setTotalQuestions(rs.getString("totalQuestion"));
		    try {
		    	System.out.println("inside today score ==> " +rs.getInt("todayScore"));
		    	logger.info("inside today score ==> " +rs.getInt("todayScore"));
		    	activePlayerDetails.setTodayScore(rs.getInt("todayScore"));
		    	
		    }
		    catch(Exception e) {
		    	e.printStackTrace();
		    }
		    return activePlayerDetails;
		
	}

}
