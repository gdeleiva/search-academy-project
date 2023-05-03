package co.empathy.academy.search.imdb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

public interface SearchAPI {

    @Operation(summary = "Get 100 movies, with an average rating of 7 or higher, that were released during 1990" +
            "or after, with more than 80k votes and ordered by averageRating and numberOfVotes")
    @GetMapping("/movies")
    ResponseEntity<List<Object>> findrMovies() throws IOException;

    @Operation(summary = "Get 100 series, with an average rating of 7 or higher, that were released during 1990" +
            "or after, with more than 15k votes and ordered by averageRating and numberOfVotes")
    @GetMapping("/series")
    ResponseEntity<List<Object>> findrSeries() throws IOException;

    @Operation(summary = "Get 100 movies/series, filtered by name and titleType")
    @Parameter(name = "name", description = "primary title of the movie we are looking for")
    @Parameter(name = "titleType", description = "the type of document we are looking for, movies or series")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = ".", content = @Content),
            @ApiResponse(responseCode = "400", description = "We couldnt find the list", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected problem  while searching for the documents", content = @Content)
    })
    @PostMapping(path = "/filter1")
    ResponseEntity<List<Object>> filter(@RequestParam String name,
                                        @RequestParam String titleType) throws IOException;

    @Operation(summary = "Get 100 movies/series, filtered by name, titleType and genres")
    @Parameter(name = "name", description = "primary title of the movie we are looking for")
    @Parameter(name = "titleType", description = "the type of document we are looking for, movies or series")
    @Parameter(name = "genres", description = "genres that we are looking for")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = ".", content = @Content),
            @ApiResponse(responseCode = "400", description = "We couldnt find the list", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected problem  while searching for the documents", content = @Content)
    })
    @PostMapping(path = "/filter2")
    ResponseEntity<List<Object>> filter(@RequestParam String name,
                                        @RequestParam String titleType,
                                        @RequestParam String[]genres) throws IOException;


    @Operation(summary = "Get 100 movies/series, filtered by name, titleType, max and min number of votes")
    @Parameter(name = "name", description = "primary title of the movie we are looking for")
    @Parameter(name = "titleType", description = "the type of document we are looking for, movies or series")
    @Parameter(name = "max", description = "max AverageRating we are looking for")
    @Parameter(name = "min", description = "min AverageRating we are looking for")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = ".", content = @Content),
            @ApiResponse(responseCode = "400", description = "We couldnt find the list", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected problem  while searching for the documents", content = @Content)
    })
    @PostMapping(path = "/filter3")
    ResponseEntity<List<Object>> filter(@RequestParam String name,
                                        @RequestParam String titleType,
                                        @RequestParam int max,
                                        @RequestParam int min) throws IOException;


    @Operation(summary = "Get 100 movies/series, filtered by name, titleType, genres, max and min number of votes")
    @Parameter(name = "name", description = "primary title of the movie we are looking for")
    @Parameter(name = "titleType", description = "the type of document we are looking for, movies or series")
    @Parameter(name = "genres", description = "genres that we are looking for")
    @Parameter(name = "max", description = "max AverageRating we are looking for")
    @Parameter(name = "min", description = "min AverageRating we are looking for")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = ".", content = @Content),
            @ApiResponse(responseCode = "400", description = "We couldnt find the list", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected problem  while searching for the documents", content = @Content)
    })
    @PostMapping(path = "/filter4")
    ResponseEntity<List<Object>> filter(@RequestParam String name,
                                        @RequestParam String titleType,
                                        @RequestParam String[] genres,
                                        @RequestParam int max,
                                        @RequestParam int min) throws IOException;


    @Operation(summary = "Get the findr movies/series")
    @Parameter(name = "titleType", description = "the type of document we are looking for, movies or series")
    @Parameter(name = "list", description = "list with the parameters we gathered from user input data from front")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = ".", content = @Content),
            @ApiResponse(responseCode = "400", description = "We couldnt find the list", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected problem  while searching for the documents", content = @Content)
    })
    @PostMapping(path = "/findr")
    ResponseEntity<List<Object>> findr(String titleType, String[] list) throws IOException;
}
