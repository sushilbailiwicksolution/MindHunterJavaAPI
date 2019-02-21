package com.bailiwick.game_servicei.model;

public class ActivePlayerDetails {
	private String packId;
	  
	  private String remainingQuestion;
	  
	  private String score;
	  
	  private String totalQuestions;
	  private int todayScore;

	public int getTodayScore() {
		return todayScore;
	}

	public void setTodayScore(int todayScore) {
		this.todayScore = todayScore;
	}

	public String getPackId() {
		return packId;
	}

	public void setPackId(String packId) {
		this.packId = packId;
	}

	public String getRemainingQuestion() {
		return remainingQuestion;
	}

	public void setRemainingQuestion(String remainingQuestion) {
		this.remainingQuestion = remainingQuestion;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(String totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
	  

}
