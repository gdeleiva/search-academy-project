package co.empathy.academy.search.imdb.service;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;

public interface QueriesService {
    /**
     * Creates a query to find all documents
     *
     * @param query  - query to search
     * return Query to be executed
     */
    Query matchAllQuery(String query, String tittle);
}
