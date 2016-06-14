package edu.csula.datascience.elasticsearch.model;


public class Movie {
	private int movieId;
	private String title;
    private String hashTitle;
	private double rating;
	private int year;
	private double sentiment;

	public Movie(int movieId, String title, String hashTitle, double rating, int year) {
		this.movieId = movieId;
		// titles were stored as "title " in mongo
		this.title = title;
		this.hashTitle = hashTitle;
		this.rating = rating;
		this.year = year;
	}
	public Movie(int movieId, String title, String hashTitle, double rating, int year, double sentiment) {
		this.movieId = movieId;
		// titles were stored as "title " in mongo
		this.title = title;
		this.hashTitle = hashTitle;
		this.rating = rating;
		this.year = year;
		this.sentiment = sentiment;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHashTitle() {
		return hashTitle;
	}
	public void setHashTitle(String hashTitle) {
		this.hashTitle = hashTitle;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getSentiment() {
		return sentiment;
	}
	public void setSentiment(double sentiment) {
		this.sentiment = sentiment;
	}
	
}

	