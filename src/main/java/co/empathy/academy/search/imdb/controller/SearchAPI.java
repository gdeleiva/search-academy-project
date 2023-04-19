package co.empathy.academy.search.imdb.controller;

import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface SearchAPI {

    ResponseEntity<List<Object>> queryMoviesByTitle() throws IOException;
}
