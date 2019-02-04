package com.bailiwick.game_servicei.model;

public class ReqGameDetails {
	
	private AuthenticationModel authentication;
	private String gameId;

	public AuthenticationModel getAuthentication() {
		return authentication;
	}

	public String getGameId() {
		return gameId;
	}

	
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReqGameDetails [authentication=");
		builder.append(authentication);
		builder.append(", gameId=");
		builder.append(gameId);
		builder.append("]");
		return builder.toString();
	}
}
