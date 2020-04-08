package br.com.craftlife.creative.dailylogin.core;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DatesManager {
	public static Date localToDate(LocalDate local) {
		Date date = Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return date;
	}
	
	public static LocalDate dateToLocal(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
	}
}
