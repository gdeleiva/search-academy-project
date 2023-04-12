package co.empathy.academy.search.imdb.service;

import java.io.File;
import java.io.IOException;

public interface IndexService {
    /**
     * Method that indexes all tsv files provided into an elastic search index
     * @param basics contains general information about the movie.
     * @param akas mainly information about the movie title or tittles.
     * @param ratings contains information about the ratings and votes the movie gets in IMDB.
     * @param crew contains information about the writer and director.
     * @param principals contains information about the principal cast/crew.
     * @throws IOException if we dont have issues reading the file.
     */
    void indexIMDbFiles(File basics, File akas, File ratings, File crew, File principals) throws IOException;
}
