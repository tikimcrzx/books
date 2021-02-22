package com.example.books.services;

import com.example.books.domain.Book;
import com.example.books.domain.User;
import com.example.books.domain.UserBook;
import com.example.books.repositories.IBookRepository;
import com.example.books.repositories.IUserBookRepository;
import com.example.books.repositories.IUserRepository;
import com.example.books.security.UserSS;
import com.example.books.services.exceptions.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserBookService {

    @Autowired
    private IUserBookRepository _userBookRepository;

    @Autowired
    private IUserRepository _userRepository;

    @Autowired
    private IBookRepository _bookRepository;

    @Autowired
    private BookService _bookService;

    @Transactional
    public UserBook insert(UserBook userBook, Integer bookId) {
        UserSS userSS = AuthUserService.authenticated();
        User user = _userRepository.findByEmail(userSS.getUsername());
        Book book = _bookRepository.findById(bookId).orElse(null);
        userBook.setUser(user);
        userBook.setBook(book);
        userBook.setCheckoutDate(new Date(System.currentTimeMillis()));

        _userBookRepository.save(userBook);

        return userBook;
    }

    public Page<UserBook> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS userSS = AuthUserService.authenticated();

        if (userSS == null)
            throw new AuthorizationException("Access Denied!");

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        User user = _userRepository.findById(userSS.getId()).orElse(null);
        return _userBookRepository.findByUser(user, pageRequest);
    }

    public void bookLoan(Integer bookId) {
        _bookService.subtractStorage(bookId);
    }

    public void returnBook(Integer bookId) {
        _bookService.incrementStorage(bookId);
    }
}
