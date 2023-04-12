package co.empathy.academy.search.imdb.model.response;

public class PrincipalMember {
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public PrincipalMember(String fullName) {
        this.fullName = fullName;
    }
}
