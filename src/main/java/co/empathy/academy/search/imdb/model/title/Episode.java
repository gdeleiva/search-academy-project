package co.empathy.academy.search.imdb.model.title;

import co.empathy.academy.search.imdb.model.Title;

public class Episode implements Title {
    // Contains the tv episode information. Fields include:
    private String tconst; // tconst (string) - alphanumeric identifier of episode
    private String parentTconst; // parentTconst (string) - alphanumeric identifier of the parent TV Series
    private int seasonNumber; // seasonNumber (integer) – season number the episode belongs to
    private int episodeNumber; // episodeNumber (integer) – episode number of the tconst in the TV series

    public Episode(String tconst, String parentTconst, int seasonNumber, int episodeNumber) {
        this.tconst = tconst;
        this.parentTconst = parentTconst;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
    }

    public String getTconst() {
        return tconst;
    }

    public void setTconst(String tconst) {
        this.tconst = tconst;
    }

    public String getParentTconst() {
        return parentTconst;
    }

    public void setParentTconst(String parentTconst) {
        this.parentTconst = parentTconst;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    @Override
    public String getId() {
        return tconst;
    }
}
