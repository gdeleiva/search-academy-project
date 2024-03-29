package co.empathy.academy.search.imdb.service;

import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
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

    /**
     * Method that tests if the index exists.
     * @param indexName name of the index we are going to check.
     * @return true if it exists.
     * @throws IOException
     */
    boolean doesIndexExists(String indexName) throws IOException;

    /**
     * Method that deletes the index we give it if it exists.
     * @param indexName
     * @throws IOException
     */
    void deleteIndex(String indexName) throws IOException;
    /**
     * Sends the given query to ElasticSearch so it is executed in the specified index. Given that there are more
     * than size of results, only the first size are returned. Results are sorted according to sortOptions.
     * Result is returned wrapped in a AcademySearchResponse, that contains hits and facets.
     * @param indexName
     * @param query
     * @param size
     * @param sortOptions
     * @return
     * @throws IOException
     */
    List<Object> executeQuery(String indexName, Query query, int size, List<SortOptions> sortOptions);

}