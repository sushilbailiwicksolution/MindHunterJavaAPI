/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bailiwick.game_servicei.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bailiwick.game_servicei.model.GameInstance;

/**
 *
 * @author Mathur
 */
public class InstanceMapper implements RowMapper<GameInstance>{

    @Override
    public GameInstance mapRow(ResultSet rs, int i) throws SQLException {
    GameInstance res = new GameInstance();
    
    res.setId(rs.getInt("id"));
    res.setStatus(rs.getInt("status"));
    res.setNoOfUsers(rs.getInt("noOfusers"));
    
    return res;
    }
    
}
