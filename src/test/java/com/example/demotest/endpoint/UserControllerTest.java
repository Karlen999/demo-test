package com.example.demotest.endpoint;

import com.example.demotest.model.User;
import com.example.demotest.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void allUsers() throws Exception {
        User user = User.builder()
                .id(1)
                .name("poxos")
                .email("poxos@mail.com")
                .password("poxos").build();
        User user1 = User.builder()
                .id(2)
                .name("poxosik")
                .email("poxosik@mail.com")
                .password("poxosik").build();
        User user2 = User.builder()
                .id(3)
                .name("poxo")
                .email("poxo@mail.com")
                .password("poxo").build();

        List<User> users = Arrays.asList(user,user1,user2);
        when(userService.findAll()).thenReturn(users);
        MockHttpServletResponse response = mockMvc.perform(get("/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(users.size()))
                .andReturn()
                .getResponse();
        System.out.println(response);
    }

    @Test
    void userById() throws Exception {
        User user = User.builder()
                .id(2)
                .name("Valod")
                .build();
        when(userService.findById(user.getId())).thenReturn(Optional.of(user));
        mockMvc.perform(get("/user/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        .andExpect(jsonPath("id").value(user.getId()));
    }

    @Test
    void save() throws Exception {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("name", "petros");
        objectNode.put("email", "petros@mail.com");
        objectNode.put("password", "petros");

        ResultActions resultActions = mockMvc.perform(post("/user/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectNode.toString()))
                .andExpect(status().isOk());
        resultActions.andExpect(jsonPath("id", notNullValue()));
        resultActions.andExpect(jsonPath("name", equalTo("petros")));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/user/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateUser() throws Exception {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("name", "poxosik");
        objectNode.put("email", "poxosik@mail.com");
        objectNode.put("password", "poxosik");
        mockMvc.perform(put("/user/update/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectNode.toString()))
                .andExpect(status().isOk());

    }
}