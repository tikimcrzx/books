package com.example.books.resources;

import com.example.books.domain.User;
import com.example.books.dto.RegisterNewUserDTO;
import com.example.books.security.JWTUtil;
import com.example.books.security.UserSS;
import com.example.books.services.AuthUserService;
import com.example.books.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthResource {

    @Autowired
    private UserService _userService;

    @Autowired
    private JWTUtil _jwtUtil;

    @PostMapping(value = "/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterNewUserDTO registerNewUserDTO) {
        User user = _userService.fromDTO(registerNewUserDTO);
        user = _userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = AuthUserService.authenticated();
        String token = _jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }
}
