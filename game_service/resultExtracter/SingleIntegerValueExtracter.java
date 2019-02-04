package com.bailiwick.game_service.resultExtracter;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class SingleIntegerValueExtracter implements ResultSetExtractor<Integer>{

	@Override
	public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
	Integer result = null;
		while(rs.next()) {
			result = rs.getInt("noOfusers");
			System.out.println("noOfusers  ==>" + result);
		}
		return result;
	}

}
