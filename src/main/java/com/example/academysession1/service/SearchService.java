package com.example.academysession1.service;

import com.example.academysession1.service.client.SearchEngineClient;

import java.util.HashMap;

public interface SearchService {

    HashMap<String, String> clusterName(String query);

    SearchEngineClient search();
}
