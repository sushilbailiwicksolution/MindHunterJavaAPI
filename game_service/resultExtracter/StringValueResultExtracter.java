package com.bailiwick.game_service.resultExtracter;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class StringValueResultExtracter implements ResultSetExtractor<String> {

	@Override
	public String extractData(ResultSet rs) throws SQLException, DataAccessException {
		while(rs.next()) {
			
			return rs.getString(1);
		}
			
		return null;
	}

}
