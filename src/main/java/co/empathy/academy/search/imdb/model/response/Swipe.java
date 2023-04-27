package co.empathy.academy.search.imdb.model.response;

public enum Swipe {
    LIKE ("Like"),
    Dislike ( "Dislike"),
    Superlike ("Superlike"),
    Unwatch ("Unwatch");

    private String name = " ";

    Swipe(String name){
        this.name = name;
    }
}
