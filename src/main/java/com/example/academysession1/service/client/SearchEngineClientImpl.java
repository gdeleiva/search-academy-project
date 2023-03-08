package com.example.academysession1.service.client;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import java.io.IOException;

public class SearchEngineClientImpl implements SearchEngineClient {

    ClientConfiguration clientConfiguration =
            ClientConfiguration.builder().connectedTo("localhost:9200").build();
    RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
    @Override
    public RestHighLevelClient getElasticClient() throws IOException {
        return client;
    }
}
