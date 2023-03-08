package com.example.academysession1.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import com.example.academysession1.service.client.SearchEngineClient;
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

// To unit test, we can mock the dependencies of the class we're testing through Mockito
@ExtendWith(MockitoExtension.class)
class SearchServiceImplTest {

    ClientConfiguration clientConfiguration =
            ClientConfiguration.builder().connectedTo("localhost:9200").build();
    RestHighLevelClient client = RestClients.create(clientConfiguration).rest();
    @Test
    void givenQuery_whenSearch_thenReturnResult() throws IOException {
        String exampleQuery = "example query";
        String name = client.cluster().health(new ClusterHealthRequest(), RequestOptions.DEFAULT).getClusterName();
        HashMap<String, String> map = new HashMap<>();
        map.put("query", exampleQuery);
        map.put("clusterName", name);
        String expectedResult = map.get("clusterName");
        // We create a mock for the SearchEngineClient
        // We mock the call to executeQuery on the client
        // You can look at it like: "given this method is called with this parameter then it will return this result"

        SearchService searchService = new SearchServiceImpl();

        String result = searchService.clusterName(exampleQuery).get("clusterName");

        // We assert that the result of what we're testing matches what we're expecting
        assertEquals(expectedResult, result);

        // Sometimes it's useful to check whether specific methods haven been called on a mock, we can do that with Mockito
        //verify(client).clusterName(exampleQuery);
    }

/*    @Test
    void givenQuery_whenErrorDuringSearch_thenLetItPropagate() {
       String exampleQuery = "example query";
        SearchEngineClient client = mock(SearchEngineClient.class);
        // In this case we're going to simulate that the client throws an error, maybe it couldn't connect to Elastic
        given(client.getClient(exampleQuery)).willThrow(RuntimeException.class);

        SearchService searchService = new SearchServiceImpl(client);

        // In this case we just check that the exception bubbles up
        assertThrows(RuntimeException.class, () -> searchService.search(exampleQuery));
    }

    @Test
    void givenBlankQuery_whenSearch_thenDoNotExecuteQueryAndReturnEmptyString() {
       SearchEngineClient client = mock(SearchEngineClient.class);
        SearchService searchService = new SearchServiceImpl(client);
        String result = searchService.search("   ");

        assertTrue(result.isEmpty());
        // For this test we didn't need to mock any calls because we should've never called the client, we can check that with Mockito
        verifyNoInteractions(client);
    }
}
*/
}