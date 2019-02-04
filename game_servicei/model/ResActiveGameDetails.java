package com.bailiwick.game_servicei.model;

public class ResActiveGameDetails {
	
	private AuthenticationModel authentication;
	private Integer userId;
	private Integer gameId;
	private Integer packId;
	private Integer availableLifeLine;
	private Integer totalQ;
	private Integer remainingQ;
	private Integer correctAnsCount;
	private Integer wrongAnsCount;
	
	
	public AuthenticationModel getAuthentication() {
		return authentication;
	}

	public void setAuthentication(AuthenticationModel authentication) {
		this.authentication = authentication;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public Integer getPackId() {
		return packId;
	}

	public void setPackId(Integer packId) {
		this.packId = packId;
	}

	public Integer getAvailableLifeLine() {
		return availableLifeLine;
	}

	public void setAvailableLifeLine(Integer availableLifeLine) {
		this.availableLifeLine = availableLifeLine;
	}

	public Integer getTotalQ() {
		return totalQ;
	}

	public void setTotalQ(Integer totalQ) {
		this.totalQ = totalQ;
	}

	public Integer getRemainingQ() {
		return remainingQ;
	}

	public void setRemainingQ(Integer remainingQ) {
		this.remainingQ = remainingQ;
	}

	public Integer getCorrectAnsCount() {
		return correctAnsCount;
	}

	public void setCorrectAnsCount(Integer correctAnsCount) {
		this.correctAnsCount = correctAnsCount;
	}

	public Integer getWrongAnsCount() {
		return wrongAnsCount;
	}

	public void setWrongAnsCount(Integer wrongAnsCount) {
		this.wrongAnsCount = wrongAnsCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActiveGameDetails [authentication=");
		builder.append(authentication);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", gameId=");
		builder.append(gameId);
		builder.append(", packId=");
		builder.append(packId);
		builder.append(", availableLifeLine=");
		builder.append(availableLifeLine);
		builder.append(", totalQ=");
		builder.append(totalQ);
		builder.append(", remainingQ=");
		builder.append(remainingQ);
		builder.append(", correctAnsCount=");
		builder.append(correctAnsCount);
		builder.append(", wrongAnsCount=");
		builder.append(wrongAnsCount);
		builder.append("]");
		return builder.toString();
	}

	
}
