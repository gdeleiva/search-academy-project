package co.empathy.academy.search.imdb.service;

import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;

import java.util.List;

public interface QueriesService {

    /**
     * Query for float values
     * @param field name of the field we are searching for
     * @param minRange min number we are looking for
     * @param maxRange max number we are looking for
     * @return query with the given parameters.
     */
    Query queryForFloatRanges(String field, float minRange, float maxRange);
    /**
     * Query for int values
     * @param field name of the field we are searching for
     * @param minRange min number we are looking for
     * @param maxRange max number we are looking for
     * @return query with the given parameters.
     */
    Query queryForIntRanges(String field, int minRange, int maxRange);

    /**
     * Query for string values
     * @param field name of the field we are searching for
     * @param value value of the field we are searching for
     * @return the query
     */
    Query queryForStringValues(String field, String value);
    /**
     * Query for multiple string values
     * @param field name of the field we are searching for
     * @param values values of the field we are searching for
     * @return the query
     */
    Query queryForMultipleStringValues(String[] values, String field);

    /**
     * Query that does multiple queries together
     * @param queries list of queries we are going to use
     * @return combination of all the queries
     */
    Query queryForMultipleQueriesTogether(List<Query> queries);

    /**
     * Method that gives us the necessary sort options to sort movies by average rating in
     * descending order.
     * @return sort options to sort movies by average rating in descending order.
     */
    SortOptions sortByAverageRating();
    /**
     * Method that gives us the necessary sort options to sort movies by number of votes in
     * descending order.
     * @return sort options to sort movies by average rating in descending order.
     */
    SortOptions sortByNumberOfVotes();

    /**
     * Query that looks for string fields, but its not the exact text query, but a similar term one,
     * which returns all movies with a similar text parameter to the one given by value.
     * @param field name of the field we are looking for
     * @param value value of the string field we are going for
     * @return
     */
    Query queryForTitles(String field, String value);
}
