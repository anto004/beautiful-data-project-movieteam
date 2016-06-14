package edu.csula.datascience.elasticsearch;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoCursor;
import edu.csula.datascience.elasticsearch.model.Movie;
import edu.csula.datascience.utilities.MongoUtilities;

public class MongoMovieImporter {

	private List<Movie> movies = new ArrayList<>();

	public void importMovies() {
		MongoUtilities mongo = new MongoUtilities("movie-data", "csv_files");
		MongoCursor<Document> cursor = mongo.getCollection().find().iterator();
		int counter = 0;
		while (cursor.hasNext() && counter < 400) {
			Document document = cursor.next();
			if (validateDocument(document)) {
				Movie movie = new Movie(document.getInteger("movieID"), document.getString("title"),
						document.getString("hashtagTitle"), document.getDouble("rating"), document.getInteger("year"));
				movies.add(movie);
			}
			counter++;
		}
	}

	public boolean validateDocument(Document document) {
		boolean docValid = false;
		docValid = validateValue(document.getString("title"));
		return docValid;
	}
	
	protected boolean validateValue(String value) {
		boolean valueValid = false;
		if (value != null && !value.isEmpty()) {
			valueValid = true;
		}
		return valueValid;
	}
	
	public List<Movie> getMovies() {
		return movies;
	}
}
