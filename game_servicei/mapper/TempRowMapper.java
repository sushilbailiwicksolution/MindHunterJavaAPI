package com.bailiwick.game_servicei.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bailiwick.game_servicei.model.Qimage;

public class TempRowMapper implements RowMapper<Qimage> {

	@Override
	public Qimage mapRow(ResultSet rs, int rowNum) throws SQLException {
		Qimage q = new Qimage();
		String s = rs.getString("qImage");
	int i = rs.getInt("qId");
		q.setId(i);
		q.setImage(s);
		return q;
	}

}
