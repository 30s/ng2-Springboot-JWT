package com.slk.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DateUtility {

	private static Logger LOGGER = LoggerFactory.getLogger(DateUtility.class);

	public DateUtility() {
	}

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	public boolean checkForDateRange(String startDate, String endDate) {
		DateTime start = new DateTime(startDate);
		DateTime end = new DateTime(endDate);
		if (start.getMillis() > end.getMillis()) {
			LOGGER.error("end date is smaller than start date");
			return false;
		}
		return true;
	}

	public String getFormatedStartDate(String date, String format, String timeZone) {
		if (format != null)
			dateFormat = new SimpleDateFormat(format);

		if (date != null)
			return dateFormat.format(date);
		else
			return dateFormat.format(new DateTime(new Date()).minusDays(30).toDate());
	}

	public String getFormatedEndDate(String date, String format, String timeZone) {
		if (format != null)
			dateFormat = new SimpleDateFormat(format);

		if (date != null)
			return dateFormat.format(date);
		else
			return dateFormat.format(new Date());
	}
}
