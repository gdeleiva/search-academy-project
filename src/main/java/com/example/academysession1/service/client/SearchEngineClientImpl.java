package com.example.academysession1.service.client;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import java.io.IOException;
import java.util.HashMap;

public class SearchEngineClientImpl implements SearchEngineClient {

    ClientConfiguration clientConfiguration =
            ClientConfiguration.builder().connectedTo("localhost:9200").build();
    RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
    @Override
    public HashMap<String, String> getClient(String query) throws IOException {
        String name = client.cluster().health(new ClusterHealthRequest(), RequestOptions.DEFAULT).getClusterName();
        HashMap<String, String> map = new HashMap<>();
        map.put("query", query);
        map.put("clusterName", name);
        return map;
    }
}
