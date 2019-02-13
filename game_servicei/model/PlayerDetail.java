package com.bailiwick.game_servicei.model;

public class PlayerDetail {
private int userId;
private int packId;
private int gameId;
private int instanceId;
private String rewardValue;
private String denominationType;
private int tierValue;
private String totalCorrectAnswer;
private String season;




public String getSeason() {
	return season;
}
public void setSeason(String season) {
	this.season = season;
}
public int getTierValue() {
	return tierValue;
}
public void setTierValue(int tierValue) {
	this.tierValue = tierValue;
}
public String getTotalCorrectAnswer() {
	return totalCorrectAnswer;
}
public void setTotalCorrectAnswer(String totalCorrectAnswer) {
	this.totalCorrectAnswer = totalCorrectAnswer;
}
public String getRewardValue() {
	return rewardValue;
}
public void setRewardValue(String rewardValue) {
	this.rewardValue = rewardValue;
}
public String getDenominationType() {
	return denominationType;
}
public void setDenominationType(String denominationType) {
	this.denominationType = denominationType;
}
public int getInstanceId() {
	return instanceId;
}
public void setInstanceId(int instanceId) {
	this.instanceId = instanceId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getPackId() {
	return packId;
}
public void setPackId(int packId) {
	this.packId = packId;
}
public int getGameId() {
	return gameId;
}
public void setGameId(int gameId) {
	this.gameId = gameId;
}

}
