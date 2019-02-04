package com.bailiwick.game_servicei.model;

public class ReqNextQuestion {

	private AuthenticationModel authentication;
	private String gameId;
	private String packId;
	private String season;

	public AuthenticationModel getAuthentication() {
		return authentication;
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

	public void setAuthentication(AuthenticationModel authentication) {
		this.authentication = authentication;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReqNextQuestion [authentication=");
		builder.append(authentication);
		builder.append(", gameId=");
		builder.append(gameId);
		builder.append(", packId=");
		builder.append(packId);
		builder.append(", season=");
		builder.append(season);
		builder.append("]");
		return builder.toString();
	}

}
