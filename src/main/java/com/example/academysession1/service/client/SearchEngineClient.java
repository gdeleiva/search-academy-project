package com.example.academysession1.service.client;

import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public interface SearchEngineClient {

    RestHighLevelClient getElasticClient() throws IOException;
}
