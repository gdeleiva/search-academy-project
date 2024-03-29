package co.empathy.academy.search.imdb.model.title;

import co.empathy.academy.search.imdb.model.Title;

public class Aka implements Title {
    // Contains the following information:
    private String titleId; //  titleId (string) - a tconst, an alphanumeric unique identifier of the title
    private String title;  //  title (string) – the localized title
    private String region; //  region (string) - the region for this version of the title
    private String language; //  language (string) - the language of the title
    private String[] types; //  types (array) - Enumerated set of attributes for this alternative title. One or more of the following: "alternative", "dvd", "festival", "tv", "video", "working", "original", "imdbDisplay". New values may be added in the future without warning
    private String[] attributes; // attributes (array) - Additional terms to describe this alternative title, not enumerated
    private boolean isOriginalTitle; // isOriginalTitle (boolean) – 0: not original title; 1: original title

    public Aka(String titleId, String title, String region, String language, String[] types, String[] attributes, boolean isOriginalTitle) {
        this.titleId = titleId;
        this.title = title;
        this.region = region;
        this.language = language;
        this.types = types;
        this.attributes = attributes;
        this.isOriginalTitle = isOriginalTitle;
    }

    public Aka(String titleId, String title, String region, String language, boolean isOriginalTitle) {
        this.titleId = titleId;
        this.title = title;
        this.region = region;
        this.language = language;
        this.isOriginalTitle = isOriginalTitle;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String[] getAttributes() {
        return attributes;
    }

    public void setAttributes(String[] attributes) {
        this.attributes = attributes;
    }

    public boolean isOriginalTitle() {
        return isOriginalTitle;
    }

    public void setIsOriginalTitle(boolean isOriginalTitle) {
        this.isOriginalTitle = isOriginalTitle;
    }

    @Override
    public String getId() {
        return titleId;
    }
}