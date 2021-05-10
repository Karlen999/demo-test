package com.example.demotest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(){
        super();
    }

    public BookNotFoundException(String message){
        super(message);
    }

}
