package co.empathy.academy.search.imdb.model.title;

import co.empathy.academy.search.imdb.model.Title;

public class Rating implements Title {
    // Contains the IMDb rating and votes information for titles:
    private String tconst; // tconst (string) - alphanumeric unique identifier of the title
    private float averageRating = 0; // averageRating – weighted average of all the individual user ratings
    private int numVotes = 0; // numVotes - number of votes the title has received

    public Rating(String tconst, float averageRating, int numVotes) {
        this.tconst = tconst;
        this.averageRating = averageRating;
        this.numVotes = numVotes;
    }

    public String getTconst() {
        return tconst;
    }

    public void setTconst(String tconst) {
        this.tconst = tconst;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public int getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(int numVotes) {
        this.numVotes = numVotes;
    }

    @Override
    public String getId() {
        return tconst;
    }
}
