package com.bailiwick.game_servicei.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bailiwick.game_servicei.model.PlayerDataDetail;

public class PlayerDataRowMapper implements RowMapper<PlayerDataDetail> {

	@Override
	public PlayerDataDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		PlayerDataDetail playerDataDetail = new PlayerDataDetail();
		playerDataDetail.setMobileNo(rs.getString("username"));
		playerDataDetail.setRank(rs.getInt("userRank"));
		return playerDataDetail;
	}

}
