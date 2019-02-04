package com.bailiwick.game_servicei.model;

public class ResTiersDetails {
	
	private String tierId;
	private String gameId;
	private Integer noOfQuestion;
	private Integer reward;
	
	public String getTierId() {
		return tierId;
	}
	public void setTierId(String tierId) {
		this.tierId = tierId;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	
	public Integer getNoOfQuestion() {
		return noOfQuestion;
	}
	public void setNoOfQuestion(Integer noOfQuestion) {
		this.noOfQuestion = noOfQuestion;
	}
	public Integer getReward() {
		return reward;
	}
	public void setReward(Integer reward) {
		this.reward = reward;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResTiersDetails [tierId=");
		builder.append(tierId);
		builder.append(", gameId=");
		builder.append(gameId);
		builder.append(", noOfQuestion=");
		builder.append(noOfQuestion);
		builder.append(", reward=");
		builder.append(reward);
		builder.append("]");
		return builder.toString();
	}
}
