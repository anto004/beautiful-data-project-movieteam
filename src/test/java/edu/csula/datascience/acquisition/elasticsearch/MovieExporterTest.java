package edu.csula.datascience.acquisition.elasticsearch;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
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
		exporter = new MovieExporter("darkserith", movies, 4);
	}
	
	@Test
	public void testExport() {
		exporter.exportMovies(mockMovies);
	}
	
	@Test
	public void testMovie() {
		String testStr = "Hello Love Me Not...";
		assertEquals(true, testStr.contains("#Love ") || testStr.contains("Love "));
	}
		
	@SuppressWarnings("deprecation")
	@Test
	public void validateSentimentTxtFile() {
		List<String> positiveWords = new ArrayList<>();
		List<String> negativeWords = new ArrayList<>();		
		try {
			positiveWords = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("positive-words.txt").toURI()));
			negativeWords = Files.readAllLines(Paths.get(ClassLoader.getSystemResource("negative-words.txt").toURI()));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		assertTrue(positiveWords.size() > 0 && negativeWords.size() > 0);
		assertEquals("a+", positiveWords.get(0));
		assertEquals("2-faced", negativeWords.get(0));		
		double sentiment = 0;		
		sentiment = (double) 300 / 1000;
	}
}
