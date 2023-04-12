package co.empathy.academy.search.imdb.exception;

public class FileReadingException extends RuntimeException {
    public FileReadingException(String message) {
        super(message);
    }

    public FileReadingException() {
        this("An unexpected problem occurred when reading a file");
    }
}
