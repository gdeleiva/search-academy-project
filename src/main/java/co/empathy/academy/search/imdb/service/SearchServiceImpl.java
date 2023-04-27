package co.empathy.academy.search.imdb.service;

import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SearchServiceImpl implements SearchService{
    @Autowired
    private ElasticService elasticService;
    @Autowired
    private QueriesService queriesService;


    @Override
    public List<Object> getAllMovies(String indexName, String tittle) throws IOException {
        List<Query> queries = new ArrayList<>();
        queries.add(queriesService.queryForFloatRanges("averageRating", (float) 7, Float.MAX_VALUE));
        queries.add(queriesService.queryForStringValues("titleType", "movie"));
        queries.add(queriesService.queryForIntRanges("numberOfVotes", 200000, Integer.MAX_VALUE));
        queries.add(queriesService.queryForIntRanges("startYear", 1990, Integer.MAX_VALUE));
        Query query = queriesService.queryForMultipleQueriesTogether(queries);
        List<SortOptions> sortOptions = new ArrayList<>();
        sortOptions.add(queriesService.sortByAverageRating());
        sortOptions.add(queriesService.sortByNumberOfVotes());
        return elasticService.executeQuery(indexName, query, 100, sortOptions);
    }

    @Override
    public List<Object> getAllSeries(String indexName, String tittle) throws IOException {
        List<Query> queries = new ArrayList<>();
        queries.add(queriesService.queryForFloatRanges("averageRating", (float) 7, Float.MAX_VALUE));
        queries.add(queriesService.queryForStringValues("titleType", "tvSeries"));
        queries.add(queriesService.queryForIntRanges("numberOfVotes", 100000, Integer.MAX_VALUE));
        queries.add(queriesService.queryForIntRanges("startYear", 1990, Integer.MAX_VALUE));
        Query query = queriesService.queryForMultipleQueriesTogether(queries);
        List<SortOptions> sortOptions = new ArrayList<>();
        sortOptions.add(queriesService.sortByAverageRating());
        sortOptions.add(queriesService.sortByNumberOfVotes());
        return elasticService.executeQuery(indexName, query, 100, sortOptions);
    }

    @Override
    public List<Object> getAllFiltered(String indexName, String name, String titleType) throws IOException {
        List<Query> queries = new ArrayList<>();
        queries.add(queriesService.queryForStringValues("titleType", titleType));
        queries.add(queriesService.queryForTitles("primaryTitle", name));
        if(titleType.equals("movie"))
            queries.add(queriesService.queryForIntRanges("numberOfVotes", 200000, Integer.MAX_VALUE));
        else
            queries.add(queriesService.queryForIntRanges("numberOfVotes", 100000, Integer.MAX_VALUE));
        Query query = queriesService.queryForMultipleQueriesTogether(queries);
        List<SortOptions> sortOptions = new ArrayList<>();
        sortOptions.add(queriesService.sortByAverageRating());
        sortOptions.add(queriesService.sortByNumberOfVotes());
        return elasticService.executeQuery(indexName, query, 100, sortOptions);
    }

    @Override
    public List<Object> getAllFiltered(String indexName, String name, String titleType, String[] genres) throws IOException {
        List<Query> queries = new ArrayList<>();
        queries.add(queriesService.queryForStringValues("titleType", titleType));
        queries.add(queriesService.queryForTitles("primaryTitle", name));
        if(titleType.equals("movie"))
            queries.add(queriesService.queryForIntRanges("numberOfVotes", 200000, Integer.MAX_VALUE));
        else
            queries.add(queriesService.queryForIntRanges("numberOfVotes", 100000, Integer.MAX_VALUE));        queries.add(queriesService.queryForMultipleStringValues(genres, "genres"));
        Query query = queriesService.queryForMultipleQueriesTogether(queries);
        List<SortOptions> sortOptions = new ArrayList<>();
        sortOptions.add(queriesService.sortByAverageRating());
        sortOptions.add(queriesService.sortByNumberOfVotes());
        return elasticService.executeQuery(indexName, query, 100, sortOptions);
    }

    @Override
    public List<Object> getAllFiltered(String indexName, String name, String titleType, int max, int min) throws IOException {
        List<Query> queries = new ArrayList<>();
        queries.add(queriesService.queryForStringValues("titleType", titleType));
        queries.add(queriesService.queryForTitles("primaryTitle", name));
        queries.add(queriesService.queryForFloatRanges("averageRating", (float) min, (float) max));
        if(titleType.equals("movie"))
            queries.add(queriesService.queryForIntRanges("numberOfVotes", 200000, Integer.MAX_VALUE));
        else
            queries.add(queriesService.queryForIntRanges("numberOfVotes", 100000, Integer.MAX_VALUE));        Query query = queriesService.queryForMultipleQueriesTogether(queries);
        List<SortOptions> sortOptions = new ArrayList<>();
        sortOptions.add(queriesService.sortByAverageRating());
        sortOptions.add(queriesService.sortByNumberOfVotes());
        return elasticService.executeQuery(indexName, query, 100, sortOptions);
    }

    @Override
    public List<Object> getAllFiltered(String indexName, String name, String titleType, String[] genres, int max, int min) throws IOException {
        List<Query> queries = new ArrayList<>();
        queries.add(queriesService.queryForStringValues("titleType", titleType));
        queries.add(queriesService.queryForTitles("primaryTitle", name));
        queries.add(queriesService.queryForFloatRanges("averageRating", (float) min, (float) max));
        if(titleType.equals("movie"))
            queries.add(queriesService.queryForIntRanges("numberOfVotes", 200000, Integer.MAX_VALUE));
        else
            queries.add(queriesService.queryForIntRanges("numberOfVotes", 100000, Integer.MAX_VALUE));        queries.add(queriesService.queryForMultipleStringValues(genres, "genres"));
        Query query = queriesService.queryForMultipleQueriesTogether(queries);
        List<SortOptions> sortOptions = new ArrayList<>();
        sortOptions.add(queriesService.sortByAverageRating());
        sortOptions.add(queriesService.sortByNumberOfVotes());
        return elasticService.executeQuery(indexName, query, 100, sortOptions);
    }

    @Override
    public List<Object> getAllFindr(String indexName, String titleType, String[] genres, String[] directors) throws IOException {
        List<Query> queries = new ArrayList<>();
        List<Object> total = new ArrayList<>();
        queries.add(queriesService.queryForStringValues("titleType", titleType));
        queries.add(queriesService.queryForMultipleStringValues(genres, "genres"));
        if(titleType.equals("movie"))
            queries.add(queriesService.queryForIntRanges("numberOfVotes", 200000, Integer.MAX_VALUE));
        else
            queries.add(queriesService.queryForIntRanges("numberOfVotes", 100000, Integer.MAX_VALUE));        Query query = queriesService.queryForMultipleQueriesTogether(queries);
        List<SortOptions> sortOptions = new ArrayList<>();
        sortOptions.add(queriesService.sortByAverageRating());
        sortOptions.add(queriesService.sortByNumberOfVotes());
        total.addAll(elasticService.executeQuery(indexName, queriesService.queryForMultipleStringValues(genres, "directors.fullName"), 10, sortOptions));
        total.addAll(elasticService.executeQuery(indexName, query, 100, sortOptions));
        return total;
    }


}
