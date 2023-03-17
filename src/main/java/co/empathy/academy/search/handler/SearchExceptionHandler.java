package co.empathy.academy.search.handler;

import co.empathy.academy.search.exception.UserAlreadyExistsException;
import co.empathy.academy.search.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class SearchExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {UserNotFoundException.class, NullPointerException.class})
    protected ResponseEntity<?> handleNotFound(Exception ex , WebRequest request){
        String body = "Can't find User with this id";
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    protected ResponseEntity<?> handleExistingUser(Exception ex , WebRequest request) {
        String body = "User already exists";
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {IOException.class})
    protected ResponseEntity<?> handleFileError(Exception ex, WebRequest request) {
        String body = "Failed to upload file";
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
