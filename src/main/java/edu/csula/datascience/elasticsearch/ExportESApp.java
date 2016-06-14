package edu.csula.datascience.elasticsearch;

import java.net.URISyntaxException;
import java.util.List;
import edu.csula.datascience.elasticsearch.model.*;;

public class ExportESApp {

	public static void main(String[] args) throws URISyntaxException {

		MongoMovieImporter movieImporter = new MongoMovieImporter();
		movieImporter.importMovies();
		List<Movie> movies = movieImporter.getMovies();
		
		//pass in your cluster name to the constructor
		MovieExporter movieExp = new MovieExporter("darkserith", movies, 400);
		movieExp.exportToES();
	}	
}
