package com.bailiwick.game_servicei.model;

public class PlayerDetail {
private int userId;
private int packId;
private int gameId;
private int instanceId;

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
