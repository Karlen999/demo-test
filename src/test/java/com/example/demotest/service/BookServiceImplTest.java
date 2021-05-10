package com.example.demotest.service;

import com.example.demotest.model.Book;
import com.example.demotest.model.User;
import com.example.demotest.repository.BookRepository;
import com.example.demotest.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
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
class BookServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void allBook() throws Exception {
        Book book = Book.builder()
                .id(1)
                .title("girq")
                .description("desc")
                .build();
        Book book1 = Book.builder()
                .id(2)
                .title("girq1")
                .description("desc1")
                .build();
        Book book2 = Book.builder()
                .id(3)
                .title("girq2")
                .description("desc2")
                .build();
        List<Book> books = Arrays.asList(book,book1,book2);
        when(bookRepository.findAll()).thenReturn(books);
        MockHttpServletResponse response = mockMvc.perform(get("/book")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(books.size()))
                .andReturn()
                .getResponse();
        System.out.println(response);

    }


    @Test
    void getById() throws Exception {
        Book book = Book.builder()
                .id(2)
                .title("girq1")
                .build();
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        mockMvc.perform(get("/book/{id}",2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(book.getId()));

    }


    @Test
    void save() throws Exception {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("title","girq1");
        objectNode.put("description","desc");
        ResultActions resultActions = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectNode.toString())).andExpect(status().isOk());
        resultActions.andExpect(jsonPath("id", notNullValue()));
        resultActions.andExpect(jsonPath("title", equalTo("girq1")));


    }

    @Test
    void update() throws Exception {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("title", "girq2");
        objectNode.put("description", "desc2");

        mockMvc.perform(put("/book/update/{id}",2)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectNode.toString()))
                .andExpect(status().isOk());
    }
}