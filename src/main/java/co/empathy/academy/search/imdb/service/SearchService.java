package co.empathy.academy.search.imdb.service;

import co.elastic.clients.json.JsonData;
import co.empathy.academy.search.imdb.model.AcademySearchResponse;
import co.empathy.academy.search.imdb.model.response.Movie;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SearchService {
    List<Object> getAllMovies(String indexName, String tittle) throws IOException;
}
