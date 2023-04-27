package co.empathy.academy.search.imdb.controller;

import co.empathy.academy.search.imdb.model.response.FindrResponse;
import co.empathy.academy.search.imdb.model.response.Swipe;
import co.empathy.academy.search.imdb.service.SearchServiceImpl;
import io.swagger.v3.oas.annotations.headers.Header;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/search")
public class SearchController implements SearchAPI{

    @Autowired
    private SearchServiceImpl searchService;


    @Operation(summary = "Get 100 movies, with an average rating of 7 or higher, that were released during 1990" +
            "or after, with more than 80k votes and ordered by averageRating and numberOfVotes")
    @GetMapping("/movies")
    public ResponseEntity<List<Object>> findrMovies() throws IOException {
        List<Object> searchResponse = searchService.getAllMovies("movies","");
        return ResponseEntity.ok(searchResponse);
    }

    @Operation(summary = "Get 100 series, with an average rating of 7 or higher, that were released during 1990" +
            "or after, with more than 15k votes and ordered by averageRating and numberOfVotes")
    @GetMapping("/series")
    public ResponseEntity<List<Object>> findrSeries() throws IOException {
        List<Object> searchResponse = searchService.getAllSeries("movies","");
        return ResponseEntity.ok(searchResponse);
    }

    @Operation(summary = "Get 100 movies/series, filtered by name and titleType")
    @PostMapping(path = "/filter1")
    public ResponseEntity<List<Object>> filter(@RequestParam String name,
                                               @RequestParam String titleType) throws IOException {
        List<Object> searchResponse = searchService.getAllFiltered("movies",name, titleType);
        return ResponseEntity.ok(searchResponse);
    }

    @Operation(summary = "Get 100 movies/series, filtered by name, titleType and genres")
    @PostMapping(path = "/filter2")
    public ResponseEntity<List<Object>> filter(@RequestParam String name,
                                               @RequestParam String titleType,
                                               @RequestParam String[]genres) throws IOException {
        List<Object> searchResponse = searchService.getAllFiltered("movies",name, titleType, genres);
        return ResponseEntity.ok(searchResponse);
    }

    @Operation(summary = "Get 100 movies/series, filtered by name, titleType, max and min number of votes")
    @PostMapping(path = "/filter3")
    public ResponseEntity<List<Object>> filter(@RequestParam String name,
                                               @RequestParam String titleType,
                                               @RequestParam int max,
                                               @RequestParam int min) throws IOException {
        List<Object> searchResponse = searchService.getAllFiltered("movies",name, titleType, min, max);
        return ResponseEntity.ok(searchResponse);
    }

    @Operation(summary = "Get 100 movies/series, filtered by name, titleType, genres, max and min number of votes")
    @PostMapping(path = "/filter4")
    public ResponseEntity<List<Object>> filter(@RequestParam String name,
                                               @RequestParam String titleType,
                                               @RequestParam String[] genres, int max, int min) throws IOException {
        List<Object> searchResponse = searchService.getAllFiltered("movies",name, titleType, genres, min, max);
        return ResponseEntity.ok(searchResponse);
    }

    @Operation(summary = "Get the findr movies/series")
    @PostMapping(path = "/findr",
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Object>> findr(@RequestParam String titleType,
                                              @RequestParam List<String[]> list) throws IOException {
        FindrResponse findr = new FindrResponse(list);
        List<Object> searchResponse = searchService.getAllFindr("movies", titleType, findr.getGenres(), findr.getDirectors());
        return ResponseEntity.ok(searchResponse);
    }
}
