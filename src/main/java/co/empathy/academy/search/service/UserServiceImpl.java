package co.empathy.academy.search.service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import com.fasterxml.jackson.core.type.TypeReference;

import co.empathy.academy.search.exception.UserAlreadyExistsException;
import co.empathy.academy.search.exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import co.empathy.academy.search.entity.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@EnableAsync
public class UserServiceImpl implements UserService{

    private ConcurrentHashMap<Long, User> map;

    public UserServiceImpl() {
        this.map = new ConcurrentHashMap<>();
    }
    @Override
    public List<User> getUsers() {
        List<User> response = new ArrayList<>();
        for (Map.Entry<Long, User> entry : this.map.entrySet()) {
            response.add(entry.getValue());
        }
        return response;
    }

    @Override
    public User getUser(Long id) throws UserNotFoundException {
        User response = null;
        if (this.map.containsKey(id)) {
            response = this.map.get(id);
        } else {
            throw new UserNotFoundException("Can't find user");
        }
        return response;
    }

    @Override
    public void addUser(User user) throws UserAlreadyExistsException {
        if (!this.map.containsKey(user.getId())) {
            this.map.put(user.getId(), user);
        } else {
            throw new UserAlreadyExistsException("User already in memory");
        }
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        if (this.map.containsKey(id)) {
            this.map.remove(id);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public void updateUser(User user) throws UserAlreadyExistsException {
        if (this.map.containsKey(user.getId())) {
            this.map.replace(user.getId(), user);
        } else {
            throw new UserAlreadyExistsException("User not found");
        }
    }


    @Override
    public CompletableFuture saveFile(MultipartFile file) throws IOException, UserAlreadyExistsException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = objectMapper.readValue(file.getInputStream(), new TypeReference<List<User>>(){});
        for (User user: users) {
            if (!this.map.containsKey(user.getId())) {
                this.map.put(user.getId(), user);
            } else {
                throw new UserAlreadyExistsException("User already in memory");
            }
        }
        return CompletableFuture.completedFuture(users);
    }
}

