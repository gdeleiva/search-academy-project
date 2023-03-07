package co.empathy.academy.search;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.tomcat.util.json.ParseException;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class HelloWorldController {

    // Create the low-level client
    ClientConfiguration clientConfiguration =
            ClientConfiguration.builder().connectedTo("localhost:9200").build();
    RestHighLevelClient client = RestClients.create(clientConfiguration).rest();

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        return "Hello " + name;
    }



    @GetMapping("/search/{query}")
    public HashMap<String, String> json(@PathVariable String query) throws IOException {
        String name = client.cluster().health(new ClusterHealthRequest(), RequestOptions.DEFAULT).getClusterName();
        HashMap<String, String> map = new HashMap<>();
        map.put("query", query);
        map.put("clusterName", name);
        return map;
    }
}
