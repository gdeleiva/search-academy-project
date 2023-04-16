package co.empathy.academy.search.imdb.service;



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
}
