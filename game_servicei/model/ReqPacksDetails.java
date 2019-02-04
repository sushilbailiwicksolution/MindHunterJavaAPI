package com.bailiwick.game_servicei.model;

public class ReqPacksDetails {
	
	private AuthenticationModel authentication;
	private String gameId;
	private Integer packType;

	
	public AuthenticationModel getAuthentication() {
		return authentication;
	}

	public String getGameId() {
		return gameId;
	}

	public Integer getPackType() {
		return packType;
	}

	
	public void setPackType(Integer packType) {
		this.packType = packType;
	}

	@Override
	public String toString() {
		return "ReqPacksDetails [authentication=" + authentication + ", gameId=" + gameId + ", packType=" + packType
				+ "]";
	}

}
