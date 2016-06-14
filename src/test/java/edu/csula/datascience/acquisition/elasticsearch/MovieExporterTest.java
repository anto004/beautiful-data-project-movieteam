package edu.csula.datascience.acquisition.elasticsearch;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import edu.csula.datascience.elasticsearch.MovieExporter;
import edu.csula.datascience.elasticsearch.MongoMovieImporter;
import edu.csula.datascience.elasticsearch.model.Movie;

public class MovieExporterTest {
	private MongoMovieImporter movieDbHelper;
	List<Movie> movies;
	List<Movie> mockMovies;
	MovieExporter exporter;
	
	@Before
	public void setUp() {
		movieDbHelper = new MongoMovieImporter();
		movieDbHelper.importMovies();
		movies = movieDbHelper.getMovies();
		mockMovies = new ArrayList<>();
		mockMovies = MovieExporterTestHelpers.generateMockMovies();
//		exporter = new MovieExporter("darkserith", movies, 4);
	}
	
//	@Test
//	public void testExport() {
//		exporter.exportMovies(mockMovies);
//	}
//	
//	@Test
//	public void testMovie() {
//		String testStr = "Hello Love Me Not...";
//		assertEquals(true, testStr.contains("#Love ") || testStr.contains("Love "));
//	}
//		
//	@SuppressWarnings("deprecation")
//	@Test
//	public void validateSentimentTxtFile() {
//		List<String> positiveWords = new ArrayList<>();
//		List<String> negativeWords = new ArrayList<>();		
//		try {
//			positiveWords = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("positive-words.txt").toURI()));
//			negativeWords = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("negative-words.txt").toURI()));
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		}
//		assertTrue(positiveWords.size() > 0 && negativeWords.size() > 0);
//		assertEquals("a+", positiveWords.get(0));
//		assertEquals("2-faced", negativeWords.get(0));		
//		double sentiment = 0;		
//		sentiment = (double) 300 / 1000;
//	}
	
	@Test
	public void testMoviesToJson() throws IOException {

			//at this pt, we have the movie data we want. output to json.
			JSONObject obj = new JSONObject();
			obj.put("Name", "StaticMovieJson");
			JSONArray moviesJsonArr = new JSONArray();
			for (Movie movie : mockMovies) {
				moviesJsonArr.add(new Gson().toJson(movie));
			}
			obj.put("Movies", moviesJsonArr);
	 
			// try-with-resources statement based on post comment below :)
			try (FileWriter file = new FileWriter("/Users/James/Documents/movies.json")) {
				file.write(obj.toJSONString());
				System.out.println("Successfully Copied JSON Object to File...");
				System.out.println("\nJSON Object: " + obj);
			}
		
	}
}
