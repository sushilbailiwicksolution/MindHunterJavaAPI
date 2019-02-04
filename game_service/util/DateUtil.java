package com.bailiwick.game_service.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	private String GENERIC_DATE_TIME = "YYYY-MM-dd HH:mm:ss";
	private String GENERIC_DATE = "YYYY-MM-dd";
	
	public String timeStamp(){
		DateFormat dateFormat = new SimpleDateFormat(GENERIC_DATE_TIME);
		return dateFormat.format(new Date());
	}
	
	public LocalDate currentDate(){
		return LocalDate.now();
	}

	public String getDateStamp(String format) {
		DateTimeFormatter dateTimeFormatter = null;

		// will give us the current time and date
		LocalDateTime current = LocalDateTime.now();

		// to print in a particular format
		if(format == null)
			dateTimeFormatter = DateTimeFormatter.ofPattern(GENERIC_DATE_TIME); 
		else
			dateTimeFormatter = DateTimeFormatter.ofPattern(format); 

		return current.format(dateTimeFormatter);  
	}

	public String tomorrow(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(GENERIC_DATE_TIME);
		return sdf.format(addHoursToJavaUtilDate(date, 24));
	}

	public Date addHoursToJavaUtilDate(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return calendar.getTime();
	}
	
	public static void main(String[] args) {
		System.out.println(new DateUtil().currentDate());
	}
}
