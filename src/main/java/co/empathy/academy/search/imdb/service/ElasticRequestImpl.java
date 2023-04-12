package co.empathy.academy.search.imdb.service;

import co.empathy.academy.search.imdb.model.response.Movie;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

import jakarta.annotation.PostConstruct;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.List;

public class ElasticRequestImpl implements ElasticRequest{
    private ElasticsearchClient client;
    @PostConstruct
    private void connect() {
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 9200), new HttpHost("elasticsearch", 9200)).build();
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());
        client = new ElasticsearchClient(transport);
    }

    @Override
    public void createIndex(String indexName) throws IOException {
        CreateIndexResponse createResponse = client.indices().create(
                new CreateIndexRequest.Builder()
                        .index(indexName)
                        .build()
        );
    }

    @Override
    public void bulkIndexMovies(List<Movie> movies, String indexName) throws IOException {
        BulkRequest.Builder br = new BulkRequest.Builder();

        for (Movie movie : movies) {
            br.operations(op -> op
                    .index(idx -> idx
                            .index(indexName)
                            .id(movie.getTconst())
                            .document(movie)
                    )
            );
        }
        BulkResponse bulkResponse = client.bulk(br.build());
    }
}
