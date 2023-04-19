package co.empathy.academy.search.imdb.service;



import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.empathy.academy.search.imdb.model.response.Movie;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class ElasticServiceImpl implements ElasticService{

    private final ElasticRequest elasticRequest;

    public ElasticServiceImpl(ElasticRequest elasticRequest) {
        this.elasticRequest = elasticRequest;
    }

    @Override
    public void createIndex(String indexName) throws IOException {
        if (elasticRequest.doesIndexExists(indexName)) {
            elasticRequest.deleteIndex(indexName);
        }
        elasticRequest.createIndex(indexName);
    }
    @Override
    public void indexIMDbDocs(List<Movie> movies, String indexName) throws IOException {
        elasticRequest.bulkIndexMovies(movies, indexName);
    }

    @Override
    public void mapIndex(String name) throws IOException {
        elasticRequest.mapIndex(name);
    }

    @Override
    public void analyzeIndex(String name) throws IOException {
        elasticRequest.analyzeIndex(name);
    }

    @Override
    public List<Object> executeQuery(String indexName, Query query, Integer maxNumber, List<SortOptions> sortOptions) throws IOException {
        System.out.println("llegamos");
        return elasticRequest.executeQuery(indexName, query, maxNumber, sortOptions);
    }
}
