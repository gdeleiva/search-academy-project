package co.empathy.academy.search.imdb.parser;
import co.empathy.academy.search.imdb.model.response.Director;
import co.empathy.academy.search.imdb.model.response.Movie;
import co.empathy.academy.search.imdb.model.response.PrincipalMember;
import co.empathy.academy.search.imdb.model.title.Aka;
import co.empathy.academy.search.imdb.model.title.Principals;
import co.empathy.academy.search.imdb.model.title.Rating;

import java.io.*;
import java.util.*;
public class IMDbParser {

    private int batchNumber = 0;

    private BufferedReader basicsReader;
    private BufferedReader akasReader;
    private BufferedReader ratingsReader;
    private BufferedReader crewReader;
    private BufferedReader principalsReader;
    private Aka prevAka = null;
    private Rating prevRating = null;
    private Principals prevPrincipal = null;
    private List<Director> prevDirectors = null;
    private String prevDirectorMovieId = null;

    public IMDbParser(File basics, File akas, File ratings, File crew, File principals) throws FileNotFoundException {
        this.basicsReader = new BufferedReader(new FileReader(basics));
        this.akasReader = new BufferedReader(new FileReader(akas));
        this.ratingsReader = new BufferedReader(new FileReader(ratings));
        this.crewReader = new BufferedReader(new FileReader(crew));
        this.principalsReader = new BufferedReader(new FileReader(principals));
    }

    private void initializeData() throws IOException {
        //skip headers
        basicsReader.readLine();
        akasReader.readLine();
        ratingsReader.readLine();
        crewReader.readLine();
        principalsReader.readLine();

        //initialize data
        prevAka = parseAka(akasReader.readLine());
        prevRating = parseRating(ratingsReader.readLine());
        prevDirectors = parseDirectors(crewReader.readLine());
        prevPrincipal = parsePrincipals(principalsReader.readLine());
    }

    private void closeReaders() throws IOException {
        basicsReader.close();
        akasReader.close();
        ratingsReader.close();
        crewReader.close();
        principalsReader.close();
    }

    public List<Movie> parseData(int batchSize) throws IOException {
        List<Movie> movies = new ArrayList<>();
        String movieData = null;
        int moviesParsed = 0;
        if (batchNumber == 0) {
            initializeData();
        }

        while (moviesParsed < batchSize && (movieData = basicsReader.readLine()) != null) {
            Movie movie = parseMovie(movieData);

            while (prevRating != null && lowerMovieID(prevRating.getTconst(), movie.getTconst())) {
                prevRating = parseRating(ratingsReader.readLine());
            }
            if (prevRating != null && prevRating.getTconst().equals(movie.getTconst())) {
                movie.setAverageRating(prevRating.getAverageRating());
                movie.setNumberOfVotes(prevRating.getNumVotes());
                prevRating = parseRating(ratingsReader.readLine());
            }

            List<Aka> akas = new ArrayList<>();

            while (prevAka != null && lowerMovieID(prevAka.getTitleId(), movie.getTconst())) {
                prevAka = parseAka(akasReader.readLine());
            }
            while (prevAka != null && prevAka.getTitleId().equals(movie.getTconst())) {
                akas.add(prevAka);
                prevAka = parseAka(akasReader.readLine());
            }
            if (!akas.isEmpty()) {
                movie.setAkas(akas);
            }

            while (prevDirectors != null && lowerMovieID(prevDirectorMovieId, movie.getTconst())) {
                prevDirectors = parseDirectors(crewReader.readLine());
            }
            if (prevDirectors != null && prevDirectorMovieId.equals(movie.getTconst())) {
                movie.setDirectors(prevDirectors);
                prevDirectors = parseDirectors(crewReader.readLine());
            }

            List<Principals> starring = new ArrayList<Principals>();

            while (prevPrincipal != null && lowerMovieID(prevPrincipal.getTconst(), movie.getTconst())) {
                prevPrincipal = parsePrincipals(principalsReader.readLine());
            }
            while (prevPrincipal != null && prevPrincipal.getTconst().equals(movie.getTconst())) {
                starring.add(prevPrincipal);
                prevPrincipal = parsePrincipals(principalsReader.readLine());
            }
            if (!starring.isEmpty()) {
                movie.setStarring(starring);
            }
            // We dont wanna add movies that have no votes nor Adult movies to our index.
            if(!(movie.getAverageRating() == 0 && movie.getNumberOfVotes() == 0) && !movie.isAdult())
            {
                movies.add(movie);
            }
            moviesParsed++;
        }

        batchNumber++;

        if (movieData == null) {
            closeReaders();
        }

        return  movies;
    }

    private List<Director> parseDirectors(String line) {
        if (line != null) {
            List<Director> result = new ArrayList<>();
            String[] fields = line.split("\t");
            prevDirectorMovieId = fields[0];
            String[] directors = fields[1].split(",");
            for (String director : directors) {
                result.add(new Director(director));
            }
            return result;
        } else {
            prevDirectorMovieId = null;
            return null;
        }
    }

    private Movie parseMovie(String line) {
        if (line != null) {
            String[] fields = line.split("\t");
            return new Movie(fields[0], fields[1], fields[2],
                    fields[3], parseBoolean(fields[4]),
                    parseInteger(fields[5]), parseInteger(fields[6]),
                    parseInteger(fields[7]), parseStringArray(fields[8]));
        } else {
            return null;
        }
    }

    private Rating parseRating(String line) {
        if (line != null) {
            String[] fields = line.split("\t");
            return new Rating(fields[0], parseFloat(fields[1]),
                    parseInteger(fields[2]));
        } else {
            return null;
        }
    }

    private Aka parseAka(String line) {
        if (line != null) {
            String fields[] = line.split("\t");
            return new Aka(fields[0], fields[2], fields[3], fields[4],
                    parseBoolean(fields[7]));
        } else {
            return null;
        }
    }

    private Principals parsePrincipals(String line) {
        if (line != null) {
            String fields[] = line.split("\t");
            String category = fields[3];
            if (!category.equals("actor") && !category.equals("actress") && !category.equals("self")) {
                return new Principals("None", null, null);
            } else {
                String[] characters = fields[5].split(",");
                return new Principals(fields[0], new PrincipalMember(fields[2]), characters);
            }

        } else {
            return null;
        }
    }

    private boolean lowerMovieID(String id1, String id2) {
        return id1.compareTo(id2) < 0;
    }

    private boolean parseBoolean(String value) {
        return value == "1";
    }

    private String[] parseStringArray(String stringArray) {
        String[] array = stringArray.split(",");
        return array;
    }

    private Integer parseInteger(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private Float parseFloat(String number) {
        try {
            return Float.parseFloat(number);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}