/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bailiwick.game_servicei.model;

/**
 *
 * @author Admin
 */
public class GamePlayRequest {
   private int userId;
    private String gameId;
    private String stopPointId;
    private String packId;
    private String gameInstanceId;
    private String packType;
    private String totalQuestions;
    private String alternateMobileNumber;
    Authentication authentication;
   
    


	public String getStopPointId() {
        return stopPointId;
    }

    public void setStopPointId(String stopPointId) {
        this.stopPointId = stopPointId;
    }

    public String getPackId() {
        return packId;
    }
  
    public void setPackId(String packId) {
        this.packId = packId;
    }

    public String getGameInstanceId() {
        return gameInstanceId;
    }

    public void setGameInstanceId(String gameInstanceId) {
        this.gameInstanceId = gameInstanceId;
    }

    public String getPackType() {
        return packType;
    }

    public void setPackType(String packType) {
        this.packType = packType;
    }

    public String getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(String totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public String getAlternateMobileNumber() {
        return alternateMobileNumber;
    }

    public void setAlternateMobileNumber(String alternateMobileNumber) {
        this.alternateMobileNumber = alternateMobileNumber;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }
    
    
    
    
    
    
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
    
}
