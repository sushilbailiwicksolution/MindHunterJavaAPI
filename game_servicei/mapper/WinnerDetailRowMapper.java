package com.bailiwick.game_servicei.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bailiwick.game_servicei.model.WinnerDetail;


public class WinnerDetailRowMapper implements RowMapper<WinnerDetail> {
	
	public WinnerDetail mapRow(ResultSet rs, int i)
	        throws SQLException
	    {
		WinnerDetail playerDetail = new WinnerDetail();
	        playerDetail.setMobileNo(rs.getString("username"));
	        try
	        {
	            playerDetail.setScore(rs.getString("correctAnswerCount"));
	        }
	        catch(Exception exception) { }
	        try
	        {
	            playerDetail.setLife(rs.getString("availableLifeLine"));
	        }
	        catch(Exception exception1) { }
	        return playerDetail;
	    }


}
