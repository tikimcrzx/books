package com.example.books.services;

import com.example.books.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUserService {
    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
