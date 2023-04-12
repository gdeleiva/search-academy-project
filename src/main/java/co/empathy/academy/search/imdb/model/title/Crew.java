package co.empathy.academy.search.imdb.model.title;

import co.empathy.academy.search.imdb.model.Title;
public class Crew implements Title {
    // Contains the director and writer information for all the titles in IMDb. Fields include:
    private String tconst; // tconst (string) - alphanumeric unique identifier of the title
    private String[] directors; // directors (array of nconsts) - director(s) of the given title
    private String[] writers; // writers (array of nconsts) – writer(s) of the given titlevv

    public Crew(String tconst, String[] directors, String[] writers) {
        this.tconst = tconst;
        this.directors = directors;
        this.writers = writers;
    }

    public String getTconst() {
        return tconst;
    }

    public void setTconst(String tconst) {
        this.tconst = tconst;
    }

    public String[] getDirectors() {
        return directors;
    }

    public void setDirectors(String[] directors) {
        this.directors = directors;
    }

    public String[] getWriters() {
        return writers;
    }

    public void setWriters(String[] writers) {
        this.writers = writers;
    }

    @Override
    public String getId() {
        return tconst;
    }
}
