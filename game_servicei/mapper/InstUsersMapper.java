/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bailiwick.game_servicei.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bailiwick.game_servicei.model.GameInstanceUsers;

/**
 *
 * @author Mathur
 */
public class InstUsersMapper implements RowMapper<GameInstanceUsers> {
    
    @Override
    public GameInstanceUsers mapRow(ResultSet rs, int i) throws SQLException {
        GameInstanceUsers res = new GameInstanceUsers();
        res.setUserId(rs.getInt("userId"));
        res.setInstanceId(rs.getInt("instanceId"));
        
        return res;
    }
    
}
