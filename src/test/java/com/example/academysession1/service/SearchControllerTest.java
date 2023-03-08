package com.example.academysession1.service;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import java.io.IOException;
import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
public class SearchControllerTest {

    ClientConfiguration clientConfiguration =
            ClientConfiguration.builder().connectedTo("localhost:9200").build();
    RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
    @Test
    void testController() throws IOException {
        String name = client.cluster().health(new ClusterHealthRequest(), RequestOptions.DEFAULT).getClusterName();
        HashMap<String, String> map = new HashMap<>();
        map.put("query", "prueba");
        map.put("clusterName", name);
    }
}
