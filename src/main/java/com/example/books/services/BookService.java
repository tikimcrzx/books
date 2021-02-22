package com.example.books.services;

import com.example.books.domain.Book;
import com.example.books.repositories.IBookRepository;
import com.example.books.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private IBookRepository _bookRepository;

    @Transactional
    public Book insert(Book book) {
        book.setId(null);
        return _bookRepository.save(book);
    }

    public void incrementStorage(Integer id) {
        Book book = findById(id);
        book.addQuantity();
        _bookRepository.save(book);
    }

    public boolean subtractStorage(Integer id) {
        Book book = findById(id);

        if (book.getQuantity() > 0) {
            book.subtractQuantity();
            return false;
        }

        _bookRepository.save(book);
        return true;
    }

    public Book findById(Integer id) {
        Optional<Book> book = _bookRepository.findById(id);
        return book.orElseThrow(() -> new ObjectNotFoundException(String.
                format("Book not found! Id: %d, Type: %s", id, Book.class.getName())));
    }

    public List<Book> findAll() {
        return _bookRepository.findAll();
    }

    public Page<Book> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return _bookRepository.findAll(pageRequest);
    }
}
