package com.example.academysession1.service;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.HashMap;

@SpringBootTest
@AutoConfigureMockMvc
public class SearchControllerTest {

    @Autowired
    private MockMvc mvc;
    ClientConfiguration clientConfiguration =
            ClientConfiguration.builder().connectedTo("localhost:9200").build();
    RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
    @Test
    void testController() throws Exception {
        String name = client.cluster().health(new ClusterHealthRequest(), RequestOptions.DEFAULT).getClusterName();
        HashMap<String, String> map = new HashMap<>();

        map.put("query", "prueba");
        map.put("clusterName", name);
        mvc.perform(MockMvcRequestBuilders.get("/search/{query}","prueba"))
                .andExpect(MockMvcResultMatchers.content().json(map.toString()));
    }
}
