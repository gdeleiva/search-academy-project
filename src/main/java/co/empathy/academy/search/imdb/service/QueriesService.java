package co.empathy.academy.search.imdb.service;

import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;

import java.util.List;

public interface QueriesService {

    Query queryForFloatRanges(String field, float minRange, float maxRange);
    Query queryForIntRanges(String field, int minRange, int maxRange);
    Query queryForStringValues(String field, String value);
    Query queryForMultipleStringValues(String[] values, String field);
    Query queryForMultipleQueriesTogether(List<Query> queries);
    SortOptions sortByAverageRating();
    SortOptions sortByNumberOfVotes();
    Query queryForTitles(String field, String value);
}
