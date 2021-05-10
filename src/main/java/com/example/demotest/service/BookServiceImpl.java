package com.example.demotest.service;

import com.example.demotest.exception.BookNotFoundException;
import com.example.demotest.model.Book;
import com.example.demotest.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Override
    public Optional<Book> getById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        bookRepository.save(book);
        return book;
    }

    @Override
    public List<Book> allBook() {
        return bookRepository.findAll();
    }

    @Override
    public Book update(Book book, int id) {
        Book bookFromDb = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("Book does not exists"));
        bookFromDb.setTitle(book.getTitle());
        bookFromDb.setDescription(book.getDescription());
        return bookRepository.save(bookFromDb);
    }

    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }
}
