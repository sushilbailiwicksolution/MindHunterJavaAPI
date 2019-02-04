package com.bailiwick.game_servicei.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.bailiwick.game_servicei.model.ResGameDetails;

public class GameRowMapper implements RowMapper<ResGameDetails>{

	private Logger logger = (Logger) LogManager.getLogger(getClass());
	
	@Override
	public ResGameDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResGameDetails gameDetails = null;
		
		try {
			gameDetails = new ResGameDetails();
			gameDetails.setEndDate(rs.getString("end_date"));
			gameDetails.setgIcon(rs.getString("gIcon"));
			gameDetails.setgMode(Integer.parseInt(rs.getString("gMode")));
			gameDetails.setGtype(Integer.parseInt(rs.getString("gType")));
			gameDetails.setId(Integer.parseInt(rs.getString("id")));
			gameDetails.setSeason(rs.getString("season"));
			gameDetails.setStartDate(rs.getString("start_date"));
			gameDetails.setStatus(Integer.parseInt(rs.getString("status")));
			gameDetails.setTitle(rs.getString("title"));
			
			return gameDetails;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
