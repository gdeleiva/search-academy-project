package co.empathy.academy.search.connection;

import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

public class Connection {
    private RestClient client;
    private ElasticsearchTransport transport;
    private ElasticsearchClient clientElastic;
    public Connection(RestClient client){
        this.client = client;
        this.transport = new RestClientTransport(client, new JacksonJsonpMapper());
        this.clientElastic = new ElasticsearchClient(this.transport);
    }

    public ElasticsearchClient getClient(){
        return clientElastic;
    }

    public String getClusterName() throws IOException{
        return this.clientElastic.cluster().health().clusterName();
    }
}
