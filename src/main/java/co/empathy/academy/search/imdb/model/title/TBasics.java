package co.empathy.academy.search.imdb.model.title;

import co.empathy.academy.search.imdb.model.Title;

public class TBasics implements Title {
    // Contains the following information for titles:

    private String tconst; // tconst (string) - alphanumeric unique identifier of the title
    private String titleType; // titleType (string) – the type/format of the title (e.g. movie, short, tvseries, tvepisode, video, etc)
    private String primaryTitle; //     primaryTitle (string) – the more popular title / the title used by the filmmakers on promotional materials at the point of release
    private String originalTitle; // originalTitle (string) - original title, in the original language
    private boolean isAdult; // isAdult (boolean) - 0: non-adult title; 1: adult title
    private int startYear; // startYear (YYYY) – represents the release year of a title. In the case of TV Series, it is the series start year
    private int endYear; // endYear (YYYY) – TV Series end year. ‘\N’ for all other title types
    private int runtimeMinutes; // runtimeMinutes – primary runtime of the title, in minutes
    private String[] genres; // genres (string array) – includes up to three genres associated with the title

    public TBasics(String tconst, String titleType, String primaryTitle, String originalTitle, boolean isAdult, int startYear, int endYear, int runtimeMinutes, String[] genres) {
        this.tconst = tconst;
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtimeMinutes = runtimeMinutes;
        this.genres = genres;
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

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public void setRuntimeMinutes(int runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    @Override
    public String getId() {
        return tconst;
    }
}
