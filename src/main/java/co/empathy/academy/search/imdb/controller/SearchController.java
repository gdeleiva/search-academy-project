package co.empathy.academy.search.imdb.controller;

import co.elastic.clients.json.JsonData;
import co.empathy.academy.search.imdb.model.AcademySearchResponse;
import co.empathy.academy.search.imdb.model.response.Movie;
import co.empathy.academy.search.imdb.service.SearchServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController implements SearchAPI{

    @Autowired
    private SearchServiceImpl searchService;

    @Operation(summary = "Get 50 movies")
    @GetMapping("")
    public ResponseEntity<List<Object>> queryMoviesByTitle() throws IOException {
        List<Object> searchResponse = searchService.getAllMovies("movies","");
        return ResponseEntity.ok(searchResponse);
    }
}
