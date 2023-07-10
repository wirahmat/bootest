package com.lawencon.bootcamptest.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConvert {
	public static LocalDateTime convertDate(String date) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		final LocalDateTime dateConvert = LocalDateTime.parse(date, formatter);
		return dateConvert;
	}
}
