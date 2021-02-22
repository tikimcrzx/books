package com.example.books.services;

import com.example.books.domain.User;
import com.example.books.repositories.IUserRepository;
import com.example.books.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository _userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = _userRepository.findByEmail(email);

        if (user == null)
            throw new UsernameNotFoundException(email);

        return new UserSS(user.getId(), user.getEmail(), user.getPassword(), user.getRoles());
    }
}
