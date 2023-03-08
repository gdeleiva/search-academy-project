package com.example.academysession1.service;


import com.example.academysession1.service.client.SearchEngineClient;
import com.example.academysession1.service.client.SearchEngineClientImpl;

import java.io.IOException;
import java.util.HashMap;

public class SearchServiceImpl implements SearchService {

    private final SearchEngineClient searchEngine;

    // We use a DI approach, SearchService receives its dependencies via constructor
    // If we created the client directly in the constructor we would be strongly coupling this class to one of its dependencies
    // As a result it would be very difficult to swap implementations for different purposes and sometimes downright impossible to unit test
    public SearchServiceImpl(SearchEngineClient searchEngine) {
        this.searchEngine = searchEngine;
    }

    @Override
    public HashMap<String, String> clusterName(String query) {
        try {
            HashMap<String, String> json = searchEngine.getClient(query);
        if (!query.isBlank()) {
            return json;
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new HashMap<String, String>();
    }
}
