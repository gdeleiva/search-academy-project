package co.empathy.academy.search.controller;

import co.empathy.academy.search.entity.User;
import co.empathy.academy.search.exception.UserAlreadyExistsException;
import co.empathy.academy.search.exception.UserNotFoundException;
import co.empathy.academy.search.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControler {
    @Autowired
    private UserServiceImpl usersService;



    @Operation(summary = "Ask for the user list", responses = {
            @ApiResponse(responseCode = "200",
            description = "Get full list of users",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)))
    })
    @GetMapping("/list")
    public ResponseEntity<List<User>> getList() {
        ResponseEntity<List<User>> response = new ResponseEntity<>(this.usersService.getUsers(), HttpStatus.OK);
        return response;
    }

    @Operation(summary = "Get user by his id", responses = {
            @ApiResponse(responseCode = "200",
                    description = "We got the user successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404",
                    description = "There is no user with this id", content = @Content(mediaType = "text/plain"))
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        User user = usersService.getUser(id);
        ResponseEntity<User> response;
        if(user!=null)
            response = new ResponseEntity<>(user, HttpStatus.OK);
        else
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return response;
    }

    @Operation(summary = "Save a new user from request body", responses = {
            @ApiResponse(responseCode = "201",
                    description = "User created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400",
                    description = "User already in memory", content = @Content(mediaType = "text/plain"))
    })
    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody User newUser) throws UserAlreadyExistsException {
        ResponseEntity<User> response;
        User user = new User("prueba");
        if(newUser!=null) {
            if (newUser.getName() != null) {
                user.setName(newUser.getName());
            }
            if (newUser.getDni() != null) {
                user.setDni(newUser.getDni());
            }
            if (newUser.getEmail() != null) {
                user.setEmail(newUser.getEmail());
            }
            this.usersService.addUser(user);
            response = new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        else{
            response = new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }
        return response;
    }



    @Operation(summary = "Delete user by Id", responses = {
            @ApiResponse(responseCode = "204",
                    description = "User deleted",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "404",
                    description = "Can't find user", content = @Content(mediaType = "text/plain"))
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
        User user = usersService.getUser(id);
        ResponseEntity<User> response;
        if(user!=null) {
            this.usersService.deleteUser(id);
            response = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        else
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return response;
    }

    @Operation(summary = "Change user by Id", responses = {
            @ApiResponse(responseCode = "204",
                    description = "User modified successfully",
                    content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "404",
                    description = "There is no user with this id", content = @Content(mediaType = "text/plain"))
    })
    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<User> editUser(@PathVariable("id") Long id, @RequestBody User newUser) throws UserNotFoundException, UserAlreadyExistsException {
        User user = usersService.getUser(id);
        ResponseEntity<User> response;
        if(user!=null && newUser!=null) {
            if (newUser.getName() != null) {
                user.setName(newUser.getName());
            }
            if (newUser.getDni() != null) {
                user.setDni(newUser.getDni());
            }
            if (newUser.getEmail() != null) {
                user.setEmail(newUser.getEmail());
            }
            usersService.updateUser(user);
            response = new ResponseEntity<>(user, HttpStatus.OK);
        }
        else
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return response;
    }
}
