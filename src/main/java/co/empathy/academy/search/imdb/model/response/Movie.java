package co.empathy.academy.search.imdb.model.response;

import co.empathy.academy.search.imdb.model.title.Aka;
import co.empathy.academy.search.imdb.model.title.Principals;
import co.empathy.academy.search.imdb.model.title.Rating;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String tconst;
    private String titleType;
    private String primaryTitle;
    private String originalTitle;
    private boolean isAdult;
    private Integer startYear;
    private Integer endYear;
    private Integer runtimeMinutes;
    private String[] genres;
    private float averageRating;
    private int numberOfVotes;
    private List<Aka> akas;
    private List<Director> directors;
    private List<Principals> starring;
    private boolean featured;
    private boolean watched;
    public Movie() {}

    public Movie(String tconst, String titleType, String primaryTitle, String originalTitle, boolean isAdult, Integer startYear,
                 Integer endYear, Integer runtimeMinutes, String[] genres, float averageRating, int numberOfVotes) {
        this(tconst, titleType, primaryTitle, originalTitle, new ArrayList<Aka>(), isAdult, startYear, endYear, runtimeMinutes,
                genres, averageRating, numberOfVotes);
    }

    public Movie(String tconst, String titleType, String primaryTitle, String originalTitle, List<Aka> akas,
                 boolean isAdult, Integer startYear, Integer endYear, Integer runtimeMinutes, String[] genres, float averageRating, int numberOfVotes) {
        this.tconst = tconst;
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.akas = akas;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtimeMinutes = runtimeMinutes;
        this.genres = genres;
        this.averageRating = averageRating;
        this.numberOfVotes = numberOfVotes;
        this.featured = false;
        this.watched = false;
    }

    public String getTconst() {
        return tconst;
    }

    public void setTconst(String tconst) {
        this.tconst = tconst;
    }

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public void setPrimaryTitle(String primaryTitle) {
        this.primaryTitle = primaryTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Aka> getAkas() {
        return akas;
    }

    public void setAkas(List<Aka> akas) {
        this.akas = akas;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Integer getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public void setRuntimeMinutes(Integer runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }


    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public void addAka(Aka aka) {
        if (this.akas == null) {
            throw new IllegalStateException();
        }
        this.akas.add(aka);
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Principals> getStarring() {
        return starring;
    }

    public void setStarring(List<Principals> starring) {
        this.starring = starring;
    }

    public String getName(int i){
        return this.starring.get(i).getNconst();
    }

    public String[] getCharacters(int i){
        return this.starring.get(i).getCharacters();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "tconst='" + tconst + '\'' +
                ", titleType='" + titleType + '\'' +
                ", primaryTitle='" + primaryTitle + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", akas=" + akas +
                ", isAdult=" + isAdult +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", runtimeMinutes=" + runtimeMinutes +
                ", genres=" + Arrays.toString(genres) +
                ", averageRating=" + averageRating +
                ", numberOfVotes=" + numberOfVotes +
                '}';
    }
}
