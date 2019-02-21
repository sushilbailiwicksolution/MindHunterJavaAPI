/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bailiwick.game_servicei.model;

/**
 *
 * @author Mathur
 */


import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"gameId",
"userId",
"packId",
"instanceId",
"userLives",
"status",
"createdDate",
"updatedDate"
})
public class GameInstanceUsers {

@JsonProperty("id")
private int id;
@JsonProperty("gameId")
private int gameId;
@JsonProperty("userId")
private int userId;
@JsonProperty("packId")
private int packId;
@JsonProperty("instanceId")
private int instanceId;
@JsonProperty("userLives")
private int userLives;
@JsonProperty("status")
private int status;
@JsonProperty("createdDate")
private String createdDate;
@JsonProperty("updatedDate")
private String updatedDate;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("id")
public int getId() {
return id;
}

@JsonProperty("id")
public void setId(int id) {
this.id = id;
}

@JsonProperty("gameId")
public int getGameId() {
return gameId;
}

@JsonProperty("gameId")
public void setGameId(int gameId) {
this.gameId = gameId;
}

@JsonProperty("userId")
public int getUserId() {
return userId;
}

@JsonProperty("userId")
public void setUserId(int userId) {
this.userId = userId;
}

@JsonProperty("packId")
public int getPackId() {
return packId;
}

@JsonProperty("packId")
public void setPackId(int packId) {
this.packId = packId;
}

@JsonProperty("instanceId")
public int getInstanceId() {
return instanceId;
}

@JsonProperty("instanceId")
public void setInstanceId(int instanceId) {
this.instanceId = instanceId;
}

@JsonProperty("userLives")
public int getUserLives() {
return userLives;
}

@JsonProperty("userLives")
public void setUserLives(int userLives) {
this.userLives = userLives;
}

@JsonProperty("status")
public int getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(int status) {
this.status = status;
}

@JsonProperty("createdDate")
public String getCreatedDate() {
return createdDate;
}

@JsonProperty("createdDate")
public void setCreatedDate(String createdDate) {
this.createdDate = createdDate;
}

@JsonProperty("updatedDate")
public String getUpdatedDate() {
return updatedDate;
}

@JsonProperty("updatedDate")
public void setUpdatedDate(String updatedDate) {
this.updatedDate = updatedDate;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, int value) {
this.additionalProperties.put(name, value);
}

}