package co.empathy.academy.search.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Can't find User with this id")
public class UserNotFoundException extends Exception{

    public UserNotFoundException(String message) {
        super(message);
    }
}
