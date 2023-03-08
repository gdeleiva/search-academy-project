package com.example.academysession1.controller;


import com.example.academysession1.service.SearchService;
import com.example.academysession1.service.SearchServiceImpl;
import com.example.academysession1.service.client.SearchEngineClient;
import com.example.academysession1.service.client.SearchEngineClientImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class SearchController {
    private SearchService searchServiceImpl = new SearchServiceImpl();
    @GetMapping("/search/{query}")
    public HashMap<String, String> json(@PathVariable String query) throws IOException {
        HashMap<String, String> map = searchServiceImpl.clusterName(query);
        return map;
    }
}
