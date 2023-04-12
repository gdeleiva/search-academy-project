package co.empathy.academy.search.imdb.model.response;

public class Director {
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Director(String fullName) {
        this.fullName = fullName;
    }
}
