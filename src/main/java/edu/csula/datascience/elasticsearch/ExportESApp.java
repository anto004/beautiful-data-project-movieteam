package edu.csula.datascience.elasticsearch;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import edu.csula.datascience.elasticsearch.model.*;;

public class ExportESApp {

	public static void main(String[] args) throws URISyntaxException {
	
		MongoMovieImporter movieImporter = new MongoMovieImporter();
		movieImporter.importMovies();
		List<Movie> movies = movieImporter.getMovies();
		
		//pass in your cluster name to the constructor
//		String clusterName = "darkserith";
//		MovieExporter movieExp = new MovieExporter(clusterName, movies, 400);
//		movieExp.exportToES();
		
		String awsAddress = "http://search-beautiful-movie-team-data-dead4tatemoould2advqkyrbni.us-west-2.es.amazonaws.com/";
		AwsMovieExporter awsMovieExp = new AwsMovieExporter(movies, awsAddress);
//		awsMovieExp.exportToAwsES();
		try {
			awsMovieExp.exportMovieDataToJsonFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
