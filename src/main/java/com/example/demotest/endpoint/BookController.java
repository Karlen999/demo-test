package com.example.demotest.endpoint;

import com.example.demotest.model.Book;
import com.example.demotest.repository.BookRepository;
import com.example.demotest.repository.UserRepository;
import com.example.demotest.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    @GetMapping
    public ResponseEntity allBooks(){
        return ResponseEntity.ok(bookService.allBook());
    }

    @GetMapping("/{id}")
    public ResponseEntity getBook(@PathVariable("id") int id){
        return ResponseEntity.ok(bookService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int id){
        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody Book book){
        if (bookRepository.findByTitle(book.getTitle()).isEmpty()){
            bookService.save(book);
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/update/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable("id") int id){
        return bookService.update(book,id);
    }
}
