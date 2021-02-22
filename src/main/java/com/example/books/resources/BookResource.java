package com.example.books.resources;

import com.example.books.domain.Book;
import com.example.books.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/books")
public class BookResource {

    @Autowired
    private BookService _bookService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Book book) {
        book = _bookService.insert(book);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(book.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/increment/{id}")
    public ResponseEntity<Void> addStorage(@PathVariable Integer id) {
        _bookService.incrementStorage(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/subtract/{id}")
    public ResponseEntity<Void> subtractStorage(@PathVariable Integer id) {
        _bookService.subtractStorage(id);
        return ResponseEntity.noContent().build();
    }
}
