package edu.csula.datascience.elasticsearch.model;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class Tweet {
	final String user;
	final String text;
	final String title;
	final String hashTitle;
	final double rating;
	final String date;

	public Tweet(String user, String text, String title, String hashTitle,
			double rating, String date) {
		this.user = user;
		this.text = text;
		this.title = title;
		this.hashTitle = hashTitle;
		this.rating = rating;
		// Need parseDate to convert to a format of 'YY-MM-DD' for elastic
		// search
		this.date = parseDate(date);
	}

	public String parseDate(String date) {
		Locale dateLocale = Locale.US;
		DateTimeFormatter inFormatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z yyyy", dateLocale);
		TemporalAccessor tempDate = inFormatter.parse(date);
		DateTimeFormatter outFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
		return outFormatter.format(tempDate);
	}

	public String getUser() {
		return user;
	}

	public String getText() {
		return text;
	}

	public String getTitle() {
		return title;
	}

	public String getHashTitle() {
		return hashTitle;
	}

	public double getRating() {
		return rating;
	}

	public String getDate() {
		return date;
	}
	
	
}
