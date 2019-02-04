package com.bailiwick.game_servicei.model;

import java.util.Map;

public class AnswerData {

	private int userId;

	private int platformId;

	private int qId;

	private boolean isOptionCorrect;

	private int correctAnswer;

	private Map additionalProperties;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPlatformId() {
		return platformId;
	}

	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}

	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
	}

	public boolean isOptionCorrect() {
		return isOptionCorrect;
	}

	public void setOptionCorrect(boolean isOptionCorrect) {
		this.isOptionCorrect = isOptionCorrect;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Map getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	
	
	
}
