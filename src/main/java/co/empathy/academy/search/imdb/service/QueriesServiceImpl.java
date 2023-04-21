package co.empathy.academy.search.imdb.service;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.json.JsonData;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class QueriesServiceImpl implements QueriesService{

    @Override
    public Query rangeQuery(String field, float minRange, float maxRange) {
        return RangeQuery.of(r -> r
                .field(field)
                .gte(JsonData.of(minRange))
                .lte(JsonData.of(maxRange)))._toQuery();
    }

    @Override
    public Query rangeQuery(String field, int minRange, int maxRange) {
        return RangeQuery.of(r -> r
                .field(field)
                .gte(JsonData.of(minRange))
                .lte(JsonData.of(maxRange)))._toQuery();
    }

    @Override
    public Query termQuery(String field, String value) {
        return TermQuery.of(t->t
                .field(field)
                .value(value))
                ._toQuery();
    }

    @Override
    public Query multiMatchQuery(String query, String[] fields) {
        Query multiMatchQuery = MultiMatchQuery.of(m -> m
                .query(query)
                .fields(Arrays.stream(fields).toList()))._toQuery();

        return multiMatchQuery;
    }
}
