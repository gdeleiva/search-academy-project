package co.empathy.academy.search.imdb.util;

import co.empathy.academy.search.imdb.model.response.Director;
import co.empathy.academy.search.imdb.model.response.Movie;
import co.empathy.academy.search.imdb.model.response.PrincipalMember;
import co.empathy.academy.search.imdb.model.title.Aka;
import co.empathy.academy.search.imdb.model.title.Principals;
import co.empathy.academy.search.imdb.model.title.Rating;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public Movie parseMovie(String line, Rating prevRating) {
        if (line != null) {
            String[] fields = line.split("\t");
            return new Movie(fields[0], fields[1], fields[2],
                    fields[3], parseBoolean(fields[4]),
                    parseInteger(fields[5]), parseInteger(fields[6]),
                    parseInteger(fields[7]), parseStringArray(fields[8]),prevRating.getAverageRating(), prevRating.getNumVotes());
        } else {
            return null;
        }
    }

    public Rating parseRating(String line) {
        if (line != null) {
            String[] fields = line.split("\t");
            return new Rating(fields[0], parseFloat(fields[1]),
                    parseInteger(fields[2]));
        } else {
            return new Rating("-1", 0, 0);
        }
    }

    public Aka parseAka(String line) {
        if (line != null) {
            String[] fields = line.split("\t");
            String[] types= fields[5].split(",");
            String[] attributes = fields[6].split(",");
            return new Aka(fields[0], fields[2], fields[3], fields[4], types, attributes, parseBoolean(fields[7]));
        } else {
            return null;
        }
    }

    public Principals parsePrincipals(String line) {
        if (line != null) {
            String[] fields = line.split("\t");
            String category = fields[3];
            if (!category.equals("actor") && !category.equals("actress") && !category.equals("self")) {
                return new Principals("-1", -1, null,null,null,null);
            } else {
                String[] characters = fields[5].split(",");
                return new Principals(fields[0], parseInteger(fields[1]), new PrincipalMember(fields[2]), fields[3], fields[4], characters);
            }

        } else {
            return null;
        }
    }

    public boolean lowerMovieID(String id1, String id2) {
        return id1.compareTo(id2) < 0;
    }

    private boolean parseBoolean(String value) {
        return value.equals("1");
    }

    private String[] parseStringArray(String stringArray) {
        return stringArray.split(",");
    }

    private Integer parseInteger(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    private Float parseFloat(String number) {
        try {
            return Float.parseFloat(number);
        } catch (NumberFormatException ex) {
            return (float) -1.0;
        }
    }
}
