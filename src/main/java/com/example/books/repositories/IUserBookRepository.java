package com.example.books.repositories;

import com.example.books.domain.User;
import com.example.books.domain.UserBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IUserBookRepository extends JpaRepository<UserBook, Integer> {

    @Transactional(readOnly = true)
    Page<UserBook> findByUser(User user, Pageable pageRequest);
}
