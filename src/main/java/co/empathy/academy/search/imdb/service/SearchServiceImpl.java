package co.empathy.academy.search.imdb.service;

import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.json.JsonData;
import co.empathy.academy.search.imdb.model.AcademySearchResponse;
import co.empathy.academy.search.imdb.model.response.Movie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService{
    @Autowired
    private ElasticService elasticService;
    @Autowired
    private QueriesService queriesService;


    @Override
    public List<Object> getAllMovies(String indexName, String tittle) throws IOException {
        Query query = queriesService.matchAllQuery(indexName, tittle);
        System.out.println("query llegamos");
        System.out.println(query);
        List<SortOptions> sortOptions = new ArrayList<>();
        return elasticService.executeQuery(indexName, query, 100, sortOptions);
    }



}
