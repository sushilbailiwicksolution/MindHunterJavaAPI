package com.bailiwick.game_servicei.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.jdbc.core.RowMapper;

public class IntegerRowMapper implements RowMapper<Integer>{

	private Logger logger = (Logger) LogManager.getLogger(getClass());

	@Override
	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Integer value = null;

		try {
			value = Integer.parseInt(rs.getString("value"));

			return value;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
