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
"packId",
"noOfUsers",
"status",
"createdDate"
})
public class GameInstance {

@JsonProperty("id")
private int id;
@JsonProperty("gameId")
private int gameId;
@JsonProperty("packId")
private int packId;
@JsonProperty("noOfUsers")
private int noOfUsers;
@JsonProperty("status")
private int status;
@JsonProperty("createdDate")
private String createdDate;
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

@JsonProperty("packId")
public int getPackId() {
return packId;
}

@JsonProperty("packId")
public void setPackId(int packId) {
this.packId = packId;
}

@JsonProperty("noOfUsers")
public int getNoOfUsers() {
return noOfUsers;
}

@JsonProperty("noOfUsers")
public void setNoOfUsers(int noOfUsers) {
this.noOfUsers = noOfUsers;
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

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, int value) {
this.additionalProperties.put(name, value);
}

}
