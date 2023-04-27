package co.empathy.academy.search.imdb.service;

import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.empathy.academy.search.imdb.model.response.Movie;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import co.elastic.clients.transport.rest_client.RestClientTransport;

import jakarta.annotation.PostConstruct;
import org.apache.http.HttpHost;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ElasticRequestImpl implements ElasticRequest{
    private ElasticsearchClient client;
    @PostConstruct
    private void connect() {
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200),
                new HttpHost("elasticsearch", 9200)).build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        client = new ElasticsearchClient(transport);
    }

    @Override
    public void createIndex(String indexName) throws IOException {
        CreateIndexResponse createIndexResponse = client.indices().create(c -> c.index(indexName));
        createIndexResponse.acknowledged();
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

    @Override
    public void mapIndex(String name) throws IOException {
        InputStream map = getClass().getClassLoader().getResourceAsStream("mapping.json");
        client.indices().putMapping(p -> p.index(name).withJson(map));
    }

    @Override
    public void analyzeIndex(String name) throws IOException {
        client.indices().close(c -> c.index(name));
        InputStream analyzer = getClass().getClassLoader().getResourceAsStream("custom_analyzer.json");
        client.indices().putSettings(p -> p.index(name).withJson(analyzer));
        client.indices().open(o -> o.index(name));
    }

    @Override
    public boolean doesIndexExists(String indexName) throws IOException {
        return client.indices().exists(ExistsRequest.of(e -> e.index(indexName))).value();
    }

    @Override
    public void deleteIndex(String indexName) throws IOException {
        client.indices().delete(DeleteIndexRequest.of(d -> d.index(indexName)));
    }

    @Override
    public List<Object> executeQuery(String indexName, Query query, int size, List<SortOptions> sortOptions) {
        SearchResponse<Object> response = null;
        try {
            response =
                    client.search(i -> i
                            .index(indexName)
                            .query(query)
                            .sort(sortOptions)
                            .size(size), Object.class);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        List<Object> hits = response.hits().hits().stream().map(Hit::source).toList();
        return hits;
    }
}
