package com.example.books.dto;

import java.io.Serializable;

public class CredentialsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public CredentialsDTO() {
    }

    private String email;
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
