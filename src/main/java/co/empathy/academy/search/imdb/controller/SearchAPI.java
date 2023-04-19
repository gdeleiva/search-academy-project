package co.empathy.academy.search.imdb.controller;

import co.elastic.clients.json.JsonData;
import co.empathy.academy.search.imdb.model.AcademySearchResponse;
import co.empathy.academy.search.imdb.model.response.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

public interface SearchAPI {

    ResponseEntity<List<Object>> queryMoviesByTitle() throws IOException;
}
