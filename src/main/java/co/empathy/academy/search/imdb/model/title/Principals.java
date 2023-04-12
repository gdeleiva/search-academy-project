package co.empathy.academy.search.imdb.model.title;

import co.empathy.academy.search.imdb.model.Title;
import co.empathy.academy.search.imdb.model.response.PrincipalMember;

public class Principals implements Title {

    // Contains the principal cast/crew for titles:
    private String tconst; // tconst (string) - alphanumeric unique identifier of the title
    private int ordering;  // ordering (integer) – a number to uniquely identify rows for a given titleId
    private String nconst; // nconst (string) - alphanumeric unique identifier of the name/person
    private String category; // category (string) - the category of job that person was in
    private String job; // job (string) - the specific job title if applicable, else '\N'
    private String [] characters; // characters (string) - the name of the character played if applicable, else '\N'

    public Principals(String tconst, int ordering, String nconst, String category, String job, String[] characters) {
        this.tconst = tconst;
        this.ordering = ordering;
        this.nconst = nconst;
        this.category = category;
        this.job = job;
        this.characters = characters;
    }

    public Principals(String field, PrincipalMember principalMember, String[] characters) {
        this.tconst = tconst;
        this.ordering = ordering;
        this.nconst = nconst;
        this.category = category;
        this.job = job;
        this.characters = characters;
    }

    public String getTconst() {
        return tconst;
    }

    public void setTconst(String tconst) {
        this.tconst = tconst;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public String getNconst() {
        return nconst;
    }

    public void setNconst(String nconst) {
        this.nconst = nconst;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String[] getCharacters() {
        return characters;
    }

    public void setCharacters(String[] characters) {
        this.characters = characters;
    }

    @Override
    public String getId() {
        return tconst;
    }
}