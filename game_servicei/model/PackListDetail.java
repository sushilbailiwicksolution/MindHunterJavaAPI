package com.bailiwick.game_servicei.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


	
	public class PackListDetail
	{
	  @JsonInclude(JsonInclude.Include.NON_NULL)
	  @JsonPropertyOrder({"packId", "gameId", "packTitle", "entryFee", "description", "tAndC", "totalQ", "reward", "lifeLines", "maxPlayer", "minPlayer", "qlevel", "winnerMsg", "loserMsg", "season", "maxPlayTime", "timePerQ", "packType"})
	  @JsonProperty("packId")
	  private Integer packId;
	  @JsonProperty("gameId")
	  private Integer gameId;
	  @JsonProperty("packTitle")
	  private String packTitle;
	  @JsonProperty("entryFee")
	  private Integer entryFee;
	  @JsonProperty("description")
	  private String description;
	  @JsonProperty("tAndC")
	  private String tAndC;
	  @JsonProperty("totalQ")
	  private Integer totalQ;
	  @JsonProperty("reward")
	  private Integer reward;
	  @JsonProperty("lifeLines")
	  private Integer lifeLines;
	  @JsonProperty("maxPlayer")
	  private Integer maxPlayer;
	  @JsonProperty("minPlayer")
	  private Integer minPlayer;
	  @JsonProperty("qlevel")
	  private Integer qlevel;
	  @JsonProperty("winnerMsg")
	  private String winnerMsg;
	  @JsonProperty("loserMsg")
	  private String loserMsg;
	  @JsonProperty("season")
	  private String season;
	  @JsonProperty("maxPlayTime")
	  private Integer maxPlayTime;
	  @JsonProperty("timePerQ")
	  private Integer timePerQ;
	  @JsonProperty("packType")
	  private Integer packType;
	  private String onlinePlayer;
	  @JsonIgnore
	  private Map<String, Object> additionalProperties = new HashMap();
	  
	  public PackListDetail() {}
	  
	  @JsonProperty("packId")
	  public Integer getPackId() { return packId; }
	  
	  @JsonProperty("packId")
	  public void setPackId(Integer packId)
	  {
	    this.packId = packId;
	  }
	  
	  @JsonProperty("gameId")
	  public Integer getGameId() {
	    return gameId;
	  }
	  
	  @JsonProperty("gameId")
	  public void setGameId(Integer gameId) {
	    this.gameId = gameId;
	  }
	  
	  @JsonProperty("packTitle")
	  public String getPackTitle() {
	    return packTitle;
	  }
	  
	  @JsonProperty("packTitle")
	  public void setPackTitle(String packTitle) {
	    this.packTitle = packTitle;
	  }
	  
	  @JsonProperty("entryFee")
	  public Integer getEntryFee() {
	    return entryFee;
	  }
	  
	  @JsonProperty("entryFee")
	  public void setEntryFee(Integer entryFee) {
	    this.entryFee = entryFee;
	  }
	  
	  @JsonProperty("description")
	  public String getDescription() {
	    return description;
	  }
	  
	  @JsonProperty("description")
	  public void setDescription(String description) {
	    this.description = description;
	  }
	  
	  @JsonProperty("tAndC")
	  public String getTAndC() {
	    return tAndC;
	  }
	  
	  @JsonProperty("tAndC")
	  public void setTAndC(String tAndC) {
	    this.tAndC = tAndC;
	  }
	  
	  @JsonProperty("totalQ")
	  public Integer getTotalQ() {
	    return totalQ;
	  }
	  
	  @JsonProperty("totalQ")
	  public void setTotalQ(Integer totalQ) {
	    this.totalQ = totalQ;
	  }
	  
	  @JsonProperty("reward")
	  public Integer getReward() {
	    return reward;
	  }
	  
	  @JsonProperty("reward")
	  public void setReward(Integer reward) {
	    this.reward = reward;
	  }
	  
	  @JsonProperty("lifeLines")
	  public Integer getLifeLines() {
	    return lifeLines;
	  }
	  
	  @JsonProperty("lifeLines")
	  public void setLifeLines(Integer lifeLines) {
	    this.lifeLines = lifeLines;
	  }
	  
	  @JsonProperty("maxPlayer")
	  public Integer getMaxPlayer() {
	    return maxPlayer;
	  }
	  
	  @JsonProperty("maxPlayer")
	  public void setMaxPlayer(Integer maxPlayer) {
	    this.maxPlayer = maxPlayer;
	  }
	  
	  @JsonProperty("minPlayer")
	  public Integer getMinPlayer() {
	    return minPlayer;
	  }
	  
	  @JsonProperty("minPlayer")
	  public void setMinPlayer(Integer minPlayer) {
	    this.minPlayer = minPlayer;
	  }
	  
	  @JsonProperty("qlevel")
	  public Integer getQlevel() {
	    return qlevel;
	  }
	  
	  @JsonProperty("qlevel")
	  public void setQlevel(Integer qlevel) {
	    this.qlevel = qlevel;
	  }
	  
	  @JsonProperty("winnerMsg")
	  public String getWinnerMsg() {
	    return winnerMsg;
	  }
	  
	  @JsonProperty("winnerMsg")
	  public void setWinnerMsg(String winnerMsg) {
	    this.winnerMsg = winnerMsg;
	  }
	  
	  @JsonProperty("loserMsg")
	  public String getLoserMsg() {
	    return loserMsg;
	  }
	  
	  @JsonProperty("loserMsg")
	  public void setLoserMsg(String loserMsg) {
	    this.loserMsg = loserMsg;
	  }
	  
	  @JsonProperty("season")
	  public String getSeason() {
	    return season;
	  }
	  
	  @JsonProperty("season")
	  public void setSeason(String season) {
	    this.season = season;
	  }
	  
	  @JsonProperty("maxPlayTime")
	  public Integer getMaxPlayTime() {
	    return maxPlayTime;
	  }
	  
	  @JsonProperty("maxPlayTime")
	  public void setMaxPlayTime(Integer maxPlayTime) {
	    this.maxPlayTime = maxPlayTime;
	  }
	  
	  @JsonProperty("timePerQ")
	  public Integer getTimePerQ() {
	    return timePerQ;
	  }
	  
	  @JsonProperty("timePerQ")
	  public void setTimePerQ(Integer timePerQ) {
	    this.timePerQ = timePerQ;
	  }
	  
	  @JsonProperty("packType")
	  public Integer getPackType() {
	    return packType;
	  }
	  
	  @JsonProperty("packType")
	  public void setPackType(Integer packType) {
	    this.packType = packType;
	  }
	  
	  public String toString()
	  {
	    return "PackListDetail{packId=" + packId + ", gameId=" + gameId + ", packTitle=" + packTitle + ", entryFee=" + entryFee + ", description=" + description + ", tAndC=" + tAndC + ", totalQ=" + totalQ + ", reward=" + reward + ", lifeLines=" + lifeLines + ", maxPlayer=" + maxPlayer + ", minPlayer=" + minPlayer + ", qlevel=" + qlevel + ", winnerMsg=" + winnerMsg + ", loserMsg=" + loserMsg + ", season=" + season + ", maxPlayTime=" + maxPlayTime + ", timePerQ=" + timePerQ + ", packType=" + packType + ", additionalProperties=" + additionalProperties + '}';
	  }
	  
	  public String getOnlinePlayer() {
	    return onlinePlayer;
	  }
	  
	  public void setOnlinePlayer(String onlinePlayer) {
	    this.onlinePlayer = onlinePlayer;
	  }
	  





	  @JsonAnyGetter
	  public Map<String, Object> getAdditionalProperties()
	  {
	    return additionalProperties;
	  }
	  
	  @JsonAnySetter
	  public void setAdditionalProperty(String name, Object value) {
	    additionalProperties.put(name, value);
	  }
	}