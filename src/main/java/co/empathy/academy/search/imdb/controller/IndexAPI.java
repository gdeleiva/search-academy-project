package co.empathy.academy.search.imdb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IndexAPI {
    @Operation(summary = "Asynchronously indexes basics, akas and ratings IMDb datasets from its files")
    @Parameter(name = "basics", description = "IMDb basics file with basic film information")
    @Parameter(name = "akas", description = "IMDb akas file with movie title translations")
    @Parameter(name = "ratings", description = "IMDb ratings file with movie rating and votes information")
    @Parameter(name = "crew", description = "IMDb crew file with writes and directors information")
    @Parameter(name = "principals", description = "IMDb principals file with principal cast information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Files uploaded succesfully.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request, some file missing", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected problem  while reading the file", content = @Content)
    })
    @PostMapping(value = "", consumes = {"multipart/form-data"})
    ResponseEntity indexIMDbFiles(@RequestParam MultipartFile basics,
                                         @RequestParam MultipartFile akas,
                                         @RequestParam MultipartFile ratings,
                                         @RequestParam MultipartFile crew,
                                         @RequestParam MultipartFile principals) throws IOException;
    }
