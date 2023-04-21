package co.empathy.academy.search.imdb.service;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;

public interface QueriesService {

    Query rangeQuery(String field, float minRange, float maxRange);

    Query rangeQuery(String field, int minRange, int maxRange);

    Query termQuery(String field, String value);

    Query multiMatchQuery(String query, String[] fields);

}
