package edu.csula.datascience.acquisition.elasticsearch;

import java.util.ArrayList;
import java.util.List;
import edu.csula.datascience.elasticsearch.model.Movie;

public class MovieExporterTestHelpers {
	
	public static List<Movie> generateMockMovies() {
		List<Movie> mockMovies = new ArrayList<>();

		Movie movie1 = new Movie(1, "Jurassic ", "#Jurassic ", 2.0, 2015, -0.12323);
		Movie movie2 = new Movie(2, "Avengers ", "#Avengers ", 3.0, 2015, 1.12323);
		Movie movie3 = new Movie(3, "Lovers ", "#Lovers ", 1.0, 2015, -2.03);
		Movie movie4 = new Movie(4, "Thinker ", "#Thinker ", 4.0, 2015, 1.8699);
		mockMovies.add(movie1);
		mockMovies.add(movie2);
		mockMovies.add(movie3);
		mockMovies.add(movie4);
		
		return mockMovies;
	}
}
