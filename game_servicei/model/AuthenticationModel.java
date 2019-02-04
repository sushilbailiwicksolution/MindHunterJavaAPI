package com.bailiwick.game_servicei.model;

public class AuthenticationModel {

	private String userId;
	private String platformId;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthenticationModel [userId=");
		builder.append(userId);
		builder.append(", platformId=");
		builder.append(platformId);
		builder.append("]");
		return builder.toString();
	}
}
