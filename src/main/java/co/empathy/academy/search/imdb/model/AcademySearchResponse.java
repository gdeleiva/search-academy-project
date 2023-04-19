package co.empathy.academy.search.imdb.model;

import java.util.List;

public class AcademySearchResponse<E> {
    private List<E> hits;
    public AcademySearchResponse() {}

    public AcademySearchResponse(List<E> hits) {
        this.hits = hits;
    }
    public List<E> getHits() {
        return hits;
    }

    public void setHits(List<E> hits) {
        this.hits = hits;
    }
}