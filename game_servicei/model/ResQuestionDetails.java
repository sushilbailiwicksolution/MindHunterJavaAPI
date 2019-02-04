package com.bailiwick.game_servicei.model;

public class ResQuestionDetails {
	
	private Integer qid;
	private Integer qLevel;
	private String qText;
	private String qImage;
	private String[] ansOption;
	private String category;
	
	public Integer getQid() {
		return qid;
	}
	public ResQuestionDetails setQid(Integer qid) {
		this.qid = qid;
		return this;
	}
	public Integer getqLevel() {
		return qLevel;
	}
	public void setqLevel(Integer qLevel) {
		this.qLevel = qLevel;
	}
	public String getqText() {
		return qText;
	}
	public void setqText(String qText) {
		this.qText = qText;
	}
	public String getqImage() {
		return qImage;
	}
	public void setqImage(String qImage) {
		this.qImage = qImage;
	}
	public String[] getAnsOption() {
		return ansOption;
	}
	public void setAnsOption(String[] ansOption) {
		this.ansOption = ansOption;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuestionDetails [");
		builder.append("qid=");
		builder.append(qid);
		builder.append(", qLevel=");
		builder.append(qLevel);
		builder.append(", qText=");
		builder.append(qText);
		builder.append(", qImage=");
		builder.append(qImage);
		builder.append(", ansOption=");
		builder.append(ansOption);
		builder.append(", category=");
		builder.append(category);
		builder.append("]");
		return builder.toString();
	}
	
		
}
