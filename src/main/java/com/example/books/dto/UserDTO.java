package com.example.books.dto;

import com.example.books.domain.User;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Name is Required")
    private String name;

    @NotEmpty(message = "LastName is Required")
    private String lastName;

    @NotEmpty(message = "Email is Required")
    private String email;

    public UserDTO() {
    }

    public UserDTO(User user) {
        id = user.getId();
        name = user.getName();
        lastName = user.getLastName();
        email = user.getEmail();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
