package com.bailiwick.game_servicei.model;

public class SaveAnswerDetail {
	
	 private String qId;
	    private String selectedOption;
	    private int uId;
	    private String gameId;
	    private String packId;
	    private String gameInstanceId;
	    private String packType;
	    private String scoreCrieteria;
	    private String spentTime;
	    
	   private  Authentication authentication;
	public String getqId() {
		return qId;
	}
	public void setqId(String qId) {
		this.qId = qId;
	}
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
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
	public String getScoreCrieteria() {
		return scoreCrieteria;
	}
	public void setScoreCrieteria(String scoreCrieteria) {
		this.scoreCrieteria = scoreCrieteria;
	}
	public Authentication getAuthentication() {
		return authentication;
	}
	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}
	public String getSpentTime() {
		return spentTime;
	}
	public void setSpentTime(String spentTime) {
		this.spentTime = spentTime;
	}
	    
	    

}
