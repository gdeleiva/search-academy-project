package co.empathy.academy.search.imdb.service;

import java.io.IOException;
import java.util.List;

public interface SearchService {
    List<Object> getAllMovies(String indexName, String tittle) throws IOException;
}
