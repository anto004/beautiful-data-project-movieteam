package edu.csula.datascience.acquisition.csv;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import edu.csula.datascience.acquisition.Collector;
import edu.csula.datascience.acquisition.model.Movie;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class CsvCollector implements Collector<Movie, Movie>{
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;

    public CsvCollector(){
        // establish database connection to MongoDB
        mongoClient = new MongoClient();

        database = mongoClient.getDatabase("movie-data");

        // select collection by name `csv_files`
        collection = database.getCollection("csv_files");
    }

    //Combines ratings for the same movie into one entry
    public Collection<Movie> mungee(Collection<Movie> src){
        ArrayList<Movie> srcArray = new ArrayList(src);
        ArrayList<Movie> finalMovieEntry = new ArrayList();
        double averageRating = srcArray.get(0).getRating();
        String title = srcArray.get(0).getTitle();
        int year = 0;

        if (isValidTitle(title)) {
            //regex found on stackoverflow
            Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(title);
            title = replaceLast(title, "\\(.*?\\) ?", "");
            while(m.find()) {
                try {
                    year = Integer.parseInt(m.group(1));
                } catch (Exception e){
                    //if there is a parenthesis in the title
                }
            }
            Movie theMovie = new Movie(srcArray.get(0).getId(), title);

            theMovie.setRating(averageRating);
            theMovie.setYear(year);
            //System.out.println(theMovie.getHashtagTitle()); //removed, used to check what movies are getting saved
            finalMovieEntry.add(theMovie);
        }

        return finalMovieEntry;

    }

    public void save(Collection<Movie> data){
        List<Document> documents = data.stream()
                .map(item -> new Document()
                        .append("movieID", item.getId())
                        .append("title", item.getTitle())
                        .append("hashtagTitle", "#" + item.getHashtagTitle())
                        .append("rating", item.getRating())
                        .append("year", item.getYear()))
                .collect(Collectors.toList());

        collection.insertMany(documents);

    }

    //regex helper methods from StackOverflow
    private static boolean isValidTitle(String title){
        boolean valid = (title != null &&
                            (title.matches("^[a-zA-Z0-9_() ]*$") &&
                            (!title.replaceAll("\\s+","").isEmpty())));
        return valid;
    }

    private static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }
}
