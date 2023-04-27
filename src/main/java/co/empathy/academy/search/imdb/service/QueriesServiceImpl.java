package co.empathy.academy.search.imdb.service;

import co.elastic.clients.elasticsearch._types.FieldSort;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.json.JsonData;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class QueriesServiceImpl implements QueriesService{

    @Override
    public Query queryForFloatRanges(String field, float minRange, float maxRange) {
        return RangeQuery.of(r -> r
                .field(field)
                .gte(JsonData.of(minRange))
                .lte(JsonData.of(maxRange)))._toQuery();
    }

    @Override
    public Query queryForIntRanges(String field, int minRange, int maxRange) {
        return RangeQuery.of(r -> r
                .field(field)
                .gte(JsonData.of(minRange))
                .lte(JsonData.of(maxRange)))._toQuery();
    }

    @Override
    public Query queryForStringValues(String field, String value) {
        return TermQuery.of(t->t
                .field(field)
                .value(value))
                ._toQuery();
    }

    @Override
    public Query queryForTitles(String field, String value) {
        Query matchPhrasePrefixQuery = MatchPhrasePrefixQuery.of(m -> m
                .query(value)
                .field(field))._toQuery();
        return matchPhrasePrefixQuery;
    }

    /**
     * Creates a terms query
     *
     * @param values Values to search
     * @param field  Field to search
     * @return Query
     */
    @Override
    public Query queryForMultipleStringValues(String[] values, String field) {
        TermsQueryField termsQueryField = TermsQueryField.of(t -> t
                .value(Arrays.stream(values).toList().stream().map(FieldValue::of).collect(Collectors.toList())));

        Query query = TermsQuery.of(t -> t
                .field(field)
                .terms(termsQueryField))._toQuery();

        return query;
    }

    /**
     * Creates a must query
     *
     * @param queries List of queries to be executed
     * @return Query to be executed
     */
    @Override
    public Query queryForMultipleQueriesTogether(List<Query> queries) {
        Query boolQuery = BoolQuery.of(b -> b.must(queries))._toQuery();
        return boolQuery;
    }

    @Override
    public SortOptions sortByAverageRating() {
            SortOptions sortOptions = SortOptions.of(s -> s
                    .field(FieldSort.of(f -> f
                            .field("averageRating")
                            .order(SortOrder.Desc))));
            return sortOptions;
    }

    @Override
    public SortOptions sortByNumberOfVotes() {
        SortOptions sortOptions = SortOptions.of(s -> s
                .field(FieldSort.of(f -> f
                        .field("numberOfVotes")
                        .order(SortOrder.Desc))));
        return sortOptions;
    }
}
