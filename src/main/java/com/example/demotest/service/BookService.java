package com.example.demotest.service;

import com.example.demotest.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> getById(int id);

    Book save(Book book);

    List<Book> allBook();

    Book update(Book book, int id);

    void deleteById(int id);
}
