package co.empathy.academy.search.imdb.service;

import co.empathy.academy.search.imdb.model.response.Movie;

import java.io.IOException;
import java.util.List;

public interface ElasticRequest {

    /**
     * Method that creates an index in ElasticSearch with a given name.
     * @param indexName name given to the index we are gonna create.
     * @throws IOException fails if the index already exists.
     */
    void createIndex(String indexName) throws IOException;

    /**
     * Method that uses bulk indexing to insert the list of movies into an Elastic search index we create
     * with a given name.
     * @param movies list of movies we are inserting into the elastic search index.
     * @param indexName name given to the index we are gonna create and insert the list of movies into.
     * @throws IOException fails if the index already exists.
     */
    void bulkIndexMovies(List<Movie> movies, String indexName) throws IOException;
}