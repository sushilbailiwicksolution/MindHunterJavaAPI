package com.bailiwick.game_servicei.model;

public class ResPacksDetails {

	private Integer packId;
	private Integer gameId;
	private String packTitle;
	private Integer entryFee;
	private String description;
	private String tAndC;
	private Integer totalQ;
	private Integer reward;
	private Integer lifeLines;
	private Integer maxPlayer;
	private Integer minPlayer;
	private Integer qlevel;
	private String winnerMsg;
	private String loserMsg;
	private String season;
	private Integer maxPlayTime;
	private Integer timePerQ;
	private Integer packType;
	private Integer pointsPerQuestion;
	
	public Integer getPackId() {
		return packId;
	}
	public void setPackId(Integer packId) {
		this.packId = packId;
	}
	public Integer getGameId() {
		return gameId;
	}
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
	public String getPackTitle() {
		return packTitle;
	}
	public void setPackTitle(String packTitle) {
		this.packTitle = packTitle;
	}
	public Integer getEntryFee() {
		return entryFee;
	}
	public void setEntryFee(Integer entryFee) {
		this.entryFee = entryFee;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String gettAndC() {
		return tAndC;
	}
	public void settAndC(String tAndC) {
		this.tAndC = tAndC;
	}
	public Integer getTotalQ() {
		return totalQ;
	}
	public void setTotalQ(Integer totalQ) {
		this.totalQ = totalQ;
	}
	public Integer getReward() {
		return reward;
	}
	public void setReward(Integer reward) {
		this.reward = reward;
	}
	public Integer getLifeLines() {
		return lifeLines;
	}
	public void setLifeLines(Integer lifeLines) {
		this.lifeLines = lifeLines;
	}
	public Integer getMaxPlayer() {
		return maxPlayer;
	}
	public void setMaxPlayer(Integer maxPlayer) {
		this.maxPlayer = maxPlayer;
	}
	public Integer getMinPlayer() {
		return minPlayer;
	}
	public void setMinPlayer(Integer minPlayer) {
		this.minPlayer = minPlayer;
	}
	public Integer getQlevel() {
		return qlevel;
	}
	public void setQlevel(Integer qlevel) {
		this.qlevel = qlevel;
	}
	public String getWinnerMsg() {
		return winnerMsg;
	}
	public void setWinnerMsg(String winnerMsg) {
		this.winnerMsg = winnerMsg;
	}
	public String getLoserMsg() {
		return loserMsg;
	}
	public void setLoserMsg(String loserMsg) {
		this.loserMsg = loserMsg;
	}
	public Integer getMaxPlayTime() {
		return maxPlayTime;
	}
	public void setMaxPlayTime(Integer maxPlayTime) {
		this.maxPlayTime = maxPlayTime;
	}
	public Integer getTimePerQ() {
		return timePerQ;
	}
	public void setTimePerQ(Integer timePerQ) {
		this.timePerQ = timePerQ;
	}
	
	public Integer getPackType() {
		return packType;
	}
	public void setPackType(Integer packType) {
		this.packType = packType;
	}
	
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	
	public Integer getPointsPerQuestion() {
		return pointsPerQuestion;
	}
	public void setPointsPerQuestion(Integer pointsPerQuestion) {
		this.pointsPerQuestion = pointsPerQuestion;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResPacksDetails [packId=");
		builder.append(packId);
		builder.append(", gameId=");
		builder.append(gameId);
		builder.append(", packTitle=");
		builder.append(packTitle);
		builder.append(", entryFee=");
		builder.append(entryFee);
		builder.append(", description=");
		builder.append(description);
		builder.append(", tAndC=");
		builder.append(tAndC);
		builder.append(", totalQ=");
		builder.append(totalQ);
		builder.append(", reward=");
		builder.append(reward);
		builder.append(", lifeLines=");
		builder.append(lifeLines);
		builder.append(", maxPlayer=");
		builder.append(maxPlayer);
		builder.append(", minPlayer=");
		builder.append(minPlayer);
		builder.append(", qlevel=");
		builder.append(qlevel);
		builder.append(", winnerMsg=");
		builder.append(winnerMsg);
		builder.append(", loserMsg=");
		builder.append(loserMsg);
		builder.append(", season=");
		builder.append(season);
		builder.append(", maxPlayTime=");
		builder.append(maxPlayTime);
		builder.append(", timePerQ=");
		builder.append(timePerQ);
		builder.append(", packType=");
		builder.append(packType);
		builder.append(", pointsPerQuestion=");
		builder.append(pointsPerQuestion);
		builder.append("]");
		return builder.toString();
	}
	
		
}
