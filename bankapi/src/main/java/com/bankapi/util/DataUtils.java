package com.bankapi.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DataUtils {

	public static void main(String[] args) throws ParseException {

		LocalDate dataAgendamento = LocalDate.now();
		LocalDate dataTrasnferencia = LocalDate.of(2021, 03, 30);
		long dias = diffDays(dataAgendamento, dataTrasnferencia);
		System.out.println(dias);
	}

	public static LocalDate getDateFromString(String patternDate, String stringDate) throws ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternDate);
		LocalDate date = LocalDate.parse(stringDate, formatter);
		return date;
	}

	public static String getStringFromDate(String patternDate, LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedString = date.format(formatter);
		return formattedString;
	}

	public static long diffDays(LocalDate date1, LocalDate date2) {
		return date1.until(date2, ChronoUnit.DAYS);
	}

}
