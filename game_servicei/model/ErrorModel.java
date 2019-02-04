package com.bailiwick.game_servicei.model;

public class ErrorModel {

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public ErrorModel setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorModel [errorMessage=");
		builder.append(errorMessage);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
