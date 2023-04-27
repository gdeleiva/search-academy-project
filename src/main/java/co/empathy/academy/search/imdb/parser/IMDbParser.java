package co.empathy.academy.search.imdb.parser;
import co.empathy.academy.search.imdb.model.response.Director;
import co.empathy.academy.search.imdb.model.response.Movie;
import co.empathy.academy.search.imdb.model.title.Aka;
import co.empathy.academy.search.imdb.model.title.Principals;
import co.empathy.academy.search.imdb.model.title.Rating;
import co.empathy.academy.search.imdb.util.Parser;

import java.io.*;
import java.util.*;
public class IMDbParser {

    private int batchNumber = 0;
    private final BufferedReader basicsReader;
    private final BufferedReader akasReader;
    private final BufferedReader ratingsReader;
    private final BufferedReader crewReader;
    private final BufferedReader principalsReader;
    private Aka prevAka = null;
    private Rating prevRating = null;
    private Principals prevPrincipal = null;
    private List<Director> prevDirectors = null;
    private String prevDirectorMovieId = null;
    private final Parser parser = new Parser();

    public IMDbParser(File basics, File akas, File ratings, File crew, File principals) throws FileNotFoundException {
        this.basicsReader = new BufferedReader(new FileReader(basics));
        this.akasReader = new BufferedReader(new FileReader(akas));
        this.ratingsReader = new BufferedReader(new FileReader(ratings));
        this.crewReader = new BufferedReader(new FileReader(crew));
        this.principalsReader = new BufferedReader(new FileReader(principals));
    }

    /**
     * Method used to initialize the readers for the indexing process.
     * @throws IOException
     */
    private void initializeData() throws IOException {
        //skip headers
        basicsReader.readLine();
        akasReader.readLine();
        ratingsReader.readLine();
        crewReader.readLine();
        principalsReader.readLine();

        //initialize data
        prevAka = parser.parseAka(akasReader.readLine());
        prevRating = parser.parseRating(ratingsReader.readLine());
        prevDirectors = parseDirectors(crewReader.readLine());
        prevPrincipal = parser.parsePrincipals(principalsReader.readLine());
    }

    /**
     * Method to close readers after the indexing processs is done
     * @throws IOException
     */
    private void closeReaders() throws IOException {
        basicsReader.close();
        akasReader.close();
        ratingsReader.close();
        crewReader.close();
        principalsReader.close();
    }

    /**
     * Method to index tsv data in the class movie
     * @param batchSize number of movies we are parsing before going to the next batch
     * @return list of parsed movies.
     * @throws IOException
     */
    public List<Movie> parseData(int batchSize) throws IOException {
        List<Movie> movies = new ArrayList<>();
        String movieData = null;
        int moviesParsed = 0;
        if (batchNumber == 0) {
            initializeData();
        }

        while (moviesParsed < batchSize && (movieData = basicsReader.readLine()) != null) {
            List<Aka> akas = new ArrayList<>();
            List<Principals> starring = new ArrayList<>();
            Movie movie = parser.parseMovie(movieData, prevRating);

            while (prevAka != null && parser.lowerMovieID(prevAka.getTitleId(), movie.getTconst())) {
                prevAka = parser.parseAka(akasReader.readLine());
            }
            while (prevAka != null && prevAka.getTitleId().equals(movie.getTconst())) {
                akas.add(prevAka);
                prevAka = parser.parseAka(akasReader.readLine());
            }
            if (!akas.isEmpty()) {
                movie.setAkas(akas);
            }
            while (prevRating != null && parser.lowerMovieID(prevRating.getTconst(), movie.getTconst())) {
                prevRating = parser.parseRating(ratingsReader.readLine());
            }
            if (prevRating != null && prevRating.getTconst().equals(movie.getTconst())) {
                movie.setAverageRating(prevRating.getAverageRating());
                movie.setNumberOfVotes(prevRating.getNumVotes());
                prevRating = parser.parseRating(ratingsReader.readLine());
            }
            while (prevPrincipal != null && parser.lowerMovieID(prevPrincipal.getTconst(), movie.getTconst())) {
                prevPrincipal = parser.parsePrincipals(principalsReader.readLine());
            }
            while (prevPrincipal != null && prevPrincipal.getTconst().equals(movie.getTconst())) {
                starring.add(prevPrincipal);
                prevPrincipal = parser.parsePrincipals(principalsReader.readLine());
            }
            if (!starring.isEmpty()) {
                movie.setStarring(starring);
            }
            while (prevDirectors != null && parser.lowerMovieID(prevDirectorMovieId, movie.getTconst())) {
                prevDirectors = parseDirectors(crewReader.readLine());
            }
            if (prevDirectors != null && prevDirectorMovieId.equals(movie.getTconst())) {
                movie.setDirectors(prevDirectors);
                prevDirectors = parseDirectors(crewReader.readLine());
            }
            // We do not want to add movies that have no votes nor Adult movies to our index.
            if(movieIsValid(movie))
            {
                movies.add(movie);
                moviesParsed++;
            }
        }
        batchNumber++;
        if (movieData == null) {
            closeReaders();
        }
        return  movies;
    }

    /**
     * Method to parse directors
     * @param line
     * @return
     */
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

    /**
     * Method that tests if the movie is not adult and it has more than 0 votes and 0 averageRating
     * @param m
     * @return
     */
    private boolean movieIsValid(Movie m){
        if(m.getNumberOfVotes()==0 && m.getAverageRating() == 0)
            return false;
        return !m.isAdult();
    }


    public int getBatchNumber(){
        return batchNumber;
    }
}