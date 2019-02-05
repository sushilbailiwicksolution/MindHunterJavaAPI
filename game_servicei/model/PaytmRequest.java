package com.bailiwick.game_servicei.model;

public class PaytmRequest {
	private String amount;
	private int userId;
	private int instanseId;
	private int gameId;
	private int packId;
	private String orderId;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getInstanseId() {
		return instanseId;
	}
	public void setInstanseId(int instanseId) {
		this.instanseId = instanseId;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public int getPackId() {
		return packId;
	}
	public void setPackId(int packId) {
		this.packId = packId;
	}
	

}
