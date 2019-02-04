package com.bailiwick.game_servicei.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.jdbc.core.RowMapper;

public class SingleValueRowMapper implements RowMapper<String>{

	private Logger logger = (Logger) LogManager.getLogger(getClass());

	@Override
	public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		String value = null;

		try {
			value = rs.getString("value");

			return value;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
