package co.empathy.academy.search.imdb.service;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.json.JsonData;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class QueriesServiceImpl implements QueriesService{

    @Override
    public Query matchAllQuery(String query, String tittle) {
        return RangeQuery.of(r -> r
                .field("averageRating")
                .gte(JsonData.of((float) 8))
                .lte(JsonData.of((float) 10)))._toQuery();
    }
}
