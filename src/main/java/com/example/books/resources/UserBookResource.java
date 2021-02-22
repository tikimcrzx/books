package com.example.books.resources;

import com.example.books.services.BookService;
import com.example.books.services.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user-book")
public class UserBookResource {

    @Autowired
    private BookService _bookService;

    @Autowired
    private UserBookService _userBookService;

    @PostMapping
    public ResponseEntity<Void> loanBook() {
        return null;
    }
}
