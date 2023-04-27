package co.empathy.academy.search.imdb.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

public interface SearchAPI {

    ResponseEntity<List<Object>> findrMovies() throws IOException;

    ResponseEntity<List<Object>> findrSeries() throws IOException;

    ResponseEntity<List<Object>> filter(String name, String titleType) throws IOException;


    ResponseEntity<List<Object>> filter(String name, String titleType, String[]genres) throws IOException;


    ResponseEntity<List<Object>> filter(String name, String titleType, int max, int min) throws IOException;


    ResponseEntity<List<Object>> filter(String name, String titleType, String[] genres, int max, int min) throws IOException;


    ResponseEntity<List<Object>> findr(String titleType, List<String[]> list) throws IOException;
}
