package com.example.academysession1.service;


import com.example.academysession1.service.client.SearchEngineClient;
import com.example.academysession1.service.client.SearchEngineClientImpl;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.client.RequestOptions;

import java.io.IOException;
import java.util.HashMap;


public class SearchServiceImpl implements SearchService {

    private final SearchEngineClient searchEngine;

    // We use a DI approach, SearchService receives its dependencies via constructor
    // If we created the client directly in the constructor we would be strongly coupling this class to one of its dependencies
    // As a result it would be very difficult to swap implementations for different purposes and sometimes downright impossible to unit test
    public SearchServiceImpl() {
        this.searchEngine = new SearchEngineClientImpl();
    }

    @Override
    public HashMap<String, String> clusterName(String query) {
        try {
            String name = searchEngine.getElasticClient().cluster().health(new ClusterHealthRequest(), RequestOptions.DEFAULT).getClusterName();
            HashMap<String, String> map = new HashMap<>();
            map.put("query", query);
            map.put("clusterName", name);
        if (!query.isBlank()) {
            return map;
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new HashMap<String, String>();
    }

    @Override
    public SearchEngineClient search() {
        return searchEngine;
    }
}
