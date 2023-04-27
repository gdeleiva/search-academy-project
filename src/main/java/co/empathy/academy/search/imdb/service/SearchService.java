package co.empathy.academy.search.imdb.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SearchService {
    List<Object> getAllMovies(String indexName, String tittle) throws IOException;
    List<Object> getAllSeries(String indexName, String tittle) throws IOException;
    List<Object> getAllFiltered(String indexName, String name, String titleType) throws IOException;
    List<Object> getAllFiltered(String indexName, String name, String titleType, String[] genres) throws IOException;
    List<Object> getAllFiltered(String indexName, String name, String titleType, int max, int min) throws IOException ;
    List<Object> getAllFiltered(String indexName, String name, String titleType, String[] genres, int max, int min) throws IOException;
    List<Object> getAllFindr(String indexName, String titleType, String [] genres, String directors []) throws IOException;

}
