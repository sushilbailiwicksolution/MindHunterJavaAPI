package com.bailiwick.game_servicei.model;

public class ResSaveAnswer extends AuthenticationModel{

	private String qId;
	private boolean isOptionCorrect;
	private String correctAnswer;
	
	public String getqId() {
		return qId;
	}
	public void setqId(String qId) {
		this.qId = qId;
	}
	public boolean getIsOptionCorrect() {
		return isOptionCorrect;
	}
	public void setIsOptionCorrect(boolean isOptionCorrect) {
		this.isOptionCorrect = isOptionCorrect;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResSaveAnswer [qId=");
		builder.append(qId);
		builder.append(", isOptionCorrect=");
		builder.append(isOptionCorrect);
		builder.append(", correctAnswer=");
		builder.append(correctAnswer);
		builder.append("]");
		return builder.toString();
	}
}
