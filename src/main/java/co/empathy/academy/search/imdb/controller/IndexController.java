package co.empathy.academy.search.imdb.controller;


import co.empathy.academy.search.imdb.service.IndexService;
import co.empathy.academy.search.imdb.util.FileConversion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.jobrunr.scheduling.BackgroundJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/movies")
public class IndexController {
    @Autowired
    private IndexService indexService;

    @Operation(summary = "Asynchronously indexes basics, akas and ratings IMDb datasets from its files")
    @Parameter(name = "basics", description = "IMDb basics file with basic film information")
    @Parameter(name = "akas", description = "IMDb akas file with movie title translations")
    @Parameter(name = "ratings", description = "IMDb ratings file with movie rating and votes information")
    @Parameter(name = "crew", description = "IMDb crew file with writes and directors information")
    @Parameter(name = "principals", description = "IMDb principals file with principal cast information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Files uploaded succesfully.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Unexpected problem  while reading the file", content = @Content)
    })
    @PostMapping(value = "", consumes = {"multipart/form-data"})
    public ResponseEntity indexIMDbFiles(@RequestParam MultipartFile basics,
                                         @RequestParam MultipartFile akas,
                                         @RequestParam MultipartFile ratings,
                                         @RequestParam MultipartFile crew,
                                         @RequestParam MultipartFile principals) throws IOException {
        File basicsFile = FileConversion.convertMultipartToTempFile(basics);
        File akasFile = FileConversion.convertMultipartToTempFile(akas);
        File ratingsFile = FileConversion.convertMultipartToTempFile(ratings);
        File crewFile = FileConversion.convertMultipartToTempFile(crew);
        File principalsFile = FileConversion.convertMultipartToTempFile(principals);

        BackgroundJob.enqueue(() -> indexService.indexIMDbFiles(basicsFile, akasFile, ratingsFile, crewFile, principalsFile));
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
