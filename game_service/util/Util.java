package com.bailiwick.game_service.util;


import org.springframework.stereotype.Component;

@Component
public class Util {

	private final String NUMERIC_STRING = "0123456789";
	public static Integer currentQuestionId = 0;
	public static Integer currentQuestionIdOfPool = 0;

	Integer counter;

	public String randomNumericString(int length) {
		StringBuilder builder = new StringBuilder();
		while (length-- != 0) {
			int character = (int)(Math.random()*NUMERIC_STRING.length());
			builder.append(NUMERIC_STRING.charAt(character));
		}

		return builder.toString();
	}

	public synchronized Integer getQuestionId() {

		currentQuestionId = currentQuestionId + 1;
		return  currentQuestionId;
	}

	public synchronized Integer getQuestionIdOfPool() {

		currentQuestionIdOfPool = currentQuestionIdOfPool + 1;
		return  currentQuestionIdOfPool;
	}


	public synchronized String getTxnID(int length) {
		String rand = null;

		StringBuilder builder = new StringBuilder();
		while (length-- != 0) {
			int character = (int)(Math.random()*NUMERIC_STRING.length());
			builder.append(NUMERIC_STRING.charAt(character));
		}

		rand = builder.toString();
		if(counter < 1000) {
			counter++;
		}else{
			counter = 0;
		}

		return rand.substring(0, (rand.length() - String.valueOf(counter).length())) + counter;
	}

	public String createRequestId(int count) {
		return randomNumericString(15);
	}

	public static void main(String args[])
	{
		Util ut = new Util();
		String num = ut.randomNumericString(9);
	}
}