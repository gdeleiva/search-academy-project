package co.empathy.academy.search.imdb.controller;


import co.empathy.academy.search.imdb.service.IndexService;
import co.empathy.academy.search.imdb.util.FileConversion;
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
@RequestMapping("/index")
public class IndexController implements IndexAPI {
    @Autowired
    private IndexService indexService;
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
