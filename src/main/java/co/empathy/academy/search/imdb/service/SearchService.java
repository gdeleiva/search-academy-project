package co.empathy.academy.search.imdb.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SearchService {
    /**
     * Method to gives us movies that have more than 200k votes and a 7 average rating or higher.
     * @param indexName name of the index
     * @return list of movies that meet the parameters.
     * @throws IOException
     */
    List<Object> getAllMovies(String indexName, String tittle) throws IOException;
    /**
     * Method to gives us Series that have more than 100k votes and a 7 average rating or higher.
     * @param indexName name of the index
     * @return list of series that meet the parameters.
     * @throws IOException
     */
    List<Object> getAllSeries(String indexName, String tittle) throws IOException;
    /**
     * Method to filter the search of movies/Series
     * @param indexName name of the index
     * @param name name of the movie/series
     * @param titleType movie or series
     * @return list of movies/series that meet the parameters.
     * @throws IOException
     */
    List<Object> getAllFiltered(String indexName, String name, String titleType) throws IOException;
    /**
     * Method to filter the search of movies/Series
     * @param indexName name of the index
     * @param name name of the movie/series
     * @param titleType movie or series
     * @param genres genres we want to look for
     * @return list of movies/series that meet the parameters.
     * @throws IOException
     */
    List<Object> getAllFiltered(String indexName, String name, String titleType, String[] genres) throws IOException;
    /**
     * Method to filter the search of movies/Series
     * @param indexName name of the index
     * @param name name of the movie/series
     * @param titleType movie or series
     * @param max max averageRating we are looking for.
     * @param min min averageRating we are looking for.
     * @return list of movies/series that meet the parameters.
     * @throws IOException
     */
    List<Object> getAllFiltered(String indexName, String name, String titleType, int max, int min) throws IOException ;
    /**
     * Method to filter the search of movies/Series
     * @param indexName name of the index
     * @param name name of the movie/series
     * @param titleType movie or series
     * @param genres genres we want to look for
     * @param max max averageRating we are looking for.
     * @param min min averageRating we are looking for.
     * @return list of movies/series that meet the parameters.
     * @throws IOException
     */
    List<Object> getAllFiltered(String indexName, String name, String titleType, String[] genres, int max, int min) throws IOException;
    /**
     * Method used to get all the movies/series that meet the parameters for the user
     * @param indexName name of the index we are searching
     * @param titleType movie or series
     * @param genres genres of movie or series
     * @param directors directors that are liked the by the user
     * @return list of movies that meet the parameters.
     * @throws IOException
     */
    List<Object> getAllFindr(String indexName, String titleType, String [] genres, String directors []) throws IOException;

}
