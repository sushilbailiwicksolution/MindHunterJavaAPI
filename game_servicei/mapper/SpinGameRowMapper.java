package com.bailiwick.game_servicei.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.bailiwick.game_servicei.model.SpinBlocks;

public class SpinGameRowMapper implements RowMapper<SpinBlocks>{

	private Logger logger = (Logger) LogManager.getLogger(getClass());
	
	@Override
	public SpinBlocks mapRow(ResultSet rs, int rowNum) throws SQLException {
		SpinBlocks gameDetails = null;
		
		try {
			gameDetails = new SpinBlocks();

			gameDetails.setId(Integer.parseInt(rs.getString("id")));
			gameDetails.setImgUrl(rs.getString("imgUrl"));
			gameDetails.setPrice(Integer.parseInt(rs.getString("price")));
			gameDetails.setNextAction(Integer.parseInt(rs.getString("nextAction")));
			gameDetails.setBgColor(rs.getString("bgColor"));
			gameDetails.setText(rs.getString("text"));
			
			return gameDetails;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
