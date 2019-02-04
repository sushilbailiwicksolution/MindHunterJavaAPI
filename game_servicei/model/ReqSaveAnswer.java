package com.bailiwick.game_servicei.model;

public class ReqSaveAnswer {

	private AuthenticationModel authentication;
	private String qId;
	private String selectedOption;

	public AuthenticationModel getAuthentication() {
		return authentication;
	}

	public String getqId() {
		return qId;
	}

	public void setqId(String qId) {
		this.qId = qId;
	}

	public String getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}

	public void setAuthentication(AuthenticationModel authentication) {
		this.authentication = authentication;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReqNextQuestion [authentication=");
		builder.append(authentication);
		builder.append(", qId=");
		builder.append(qId);
		builder.append(", selectedOption=");
		builder.append(selectedOption);
		builder.append("]");
		return builder.toString();
	}
}
