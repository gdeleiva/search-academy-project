package co.empathy.academy.search.imdb.service;


import co.empathy.academy.search.imdb.model.response.Movie;
import co.empathy.academy.search.imdb.parser.IMDbParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

@Service
public class IndexServiceImpl implements IndexService{
    @Autowired
    private ElasticService elasticService;

    @Override
    public void indexIMDbFiles(File basics, File akas, File ratings, File crew, File principals) throws IOException {
        IMDbParser parser = new IMDbParser(basics, akas, ratings, crew, principals);

        List<Movie> moviesBatch = new ArrayList<>();
        String indexName = "movies";
        elasticService.createIndex(indexName);
        elasticService.analyzeIndex(indexName);
        elasticService.mapIndex(indexName);
        do {
            moviesBatch.clear();
            moviesBatch = parser.parseData(20000);
            elasticService.indexIMDbDocs(moviesBatch, indexName);
        } while (moviesBatch.size() == 20000); //&& parser.getBatchNumber() < 100);
    }
}
