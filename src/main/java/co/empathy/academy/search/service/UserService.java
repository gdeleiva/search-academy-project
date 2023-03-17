package co.empathy.academy.search.service;

import co.empathy.academy.search.entity.User;
import co.empathy.academy.search.exception.UserAlreadyExistsException;
import co.empathy.academy.search.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserService {

    List<User> getUsers();
    User getUser(Long id) throws UserNotFoundException;
    void addUser(User user) throws UserAlreadyExistsException;
    void deleteUser(Long id) throws UserNotFoundException;
    void updateUser(User user) throws UserAlreadyExistsException;
    CompletableFuture saveFile(MultipartFile file) throws IOException, InterruptedException, UserAlreadyExistsException;
}
