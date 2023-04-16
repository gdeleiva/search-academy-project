package co.empathy.academy.search.imdb.service;

import co.empathy.academy.search.imdb.model.response.Movie;

import java.io.IOException;
import java.util.List;

public interface ElasticService {
    /**
     * Method that creates an elastic search index with a given name.
     * @param indexName name of the index we are gonna create.
     * @throws IOException fails if there is already an index with the same name.
     */
    void createIndex(String indexName) throws IOException;

    /**
     * Method that inserts a list of movies in an elastic search index that already exists.
     * @param movies list of movies that are inserted in the index.
     * @param indexName name of the index we are gonna insert the movie list in.
     * @throws IOException fails if we fail creating the index.
     */
    void indexIMDbDocs(List<Movie> movies, String indexName) throws IOException;

    /**
     * Method that uses a json mapping file to map an index that we choose by its name.
     * @param name name of the index we are going to map.
     * @throws IOException fails if the index does not exist.
     */
    void mapIndex(String name) throws IOException;

    /**
     * Method that uses a json settings file to analyze an index that we choose by its name.
     * @param name name of the index we are going to analyze.
     * @throws IOException fails if the index does not exist.
     */
    void analyzeIndex(String name) throws IOException;
}
