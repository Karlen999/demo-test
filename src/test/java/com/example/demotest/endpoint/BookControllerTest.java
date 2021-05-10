package com.example.demotest.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {


//    @MockBean
//    private BookService bookService;

    @Autowired
    private MockMvc mvc;

    @Test
    void allBooks() throws Exception {
        mvc.perform(get("/book")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBook() throws Exception {
        mvc.perform(get("/book/{id}",2)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        mvc.perform(delete("/book/{id}",3)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }




    @Test
    void save() throws Exception {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("title","girq1");
        objectNode.put("description", "desc");
        ResultActions resultActions = mvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectNode.toString())).andExpect(status().isOk());
        resultActions.andExpect(jsonPath("id", notNullValue()));
        resultActions.andExpect(jsonPath("title", equalTo("girq1")));

    }

    @Test
    void updateBook() throws Exception {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("title", "girq2");
        objectNode.put("description", "desc2");

        mvc.perform(put("/book/update/{id}",3)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectNode.toString()))
                .andExpect(status().isOk());
    }

}