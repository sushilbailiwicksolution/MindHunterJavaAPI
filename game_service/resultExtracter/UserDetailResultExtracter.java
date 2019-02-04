package com.bailiwick.game_service.resultExtracter;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.bailiwick.game_servicei.model.UserLoginRequest;

/**
 *
 * @author Admin
 */
public class UserDetailResultExtracter implements ResultSetExtractor<UserLoginRequest> {

    @Override
    public UserLoginRequest extractData(ResultSet rs) throws SQLException, DataAccessException {
        UserLoginRequest result = new UserLoginRequest();
        while(rs.next()){
          
       result.setUserId(rs.getInt("user_id"));
     //  result.setUserToken(rs.getString("user_token"));
        }
        return result;

    }

}
