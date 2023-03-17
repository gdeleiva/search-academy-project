package co.empathy.academy.search;


import co.empathy.academy.search.entity.User;
import co.empathy.academy.search.exception.UserAlreadyExistsException;
import co.empathy.academy.search.exception.UserNotFoundException;
import co.empathy.academy.search.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import javax.print.attribute.standard.Media;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private User test;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;
    @BeforeEach
    void setUp() throws UserAlreadyExistsException {
        test = new User(1L,"test","test","test");
        this.userService.addUser(test);
    }

    @AfterEach
    void tearDown() throws UserNotFoundException {
        this.userService.deleteUser(1L);
    }

    @ Test
    public void whenGetUsers_thenStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void givenUser_whenGetUserById_thenReturnUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/get/{id}", test.getId().intValue())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(test.getId().intValue())))
                .andExpect(jsonPath("$.name", is(test.getName())))
                .andExpect(jsonPath("$.email", is(test.getEmail())));
    }

    @Test
    public void givenFailUser_WhenGetUser_thenStatus404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 23)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    public void givenNewUser_WhenCreateUser_thenStatus201() throws Exception {
        User newUser = new User(100L,"nuevo", "nuevo","nuevo");
        mockMvc.perform(MockMvcRequestBuilders.post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newUser)))
                .andExpect(status().is(201));
    }
    @Test
    public void givenExistingUser_WhenCreateUser_thenStatus400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(test)))
                .andExpect(status().is(400));
    }

    @Test
    public void givenNewName_WhenModifyUser_thenStatus204_thenUserIsModified() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/user/edit/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"newNameTest\"}"))
                .andExpect(status().is(200));
        assertEquals(this.userService.getUser(1L).getName(), "newNameTest");
    }

    @Test
    public void givenUserId_WhenDelete_ThenStatus204() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(204));
        this.setUp();
    }
}
