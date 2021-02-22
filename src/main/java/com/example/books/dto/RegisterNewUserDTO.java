package com.example.books.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class RegisterNewUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Name is Required")
    private String name;

    @NotEmpty(message = "LastName is Required")
    private String lastName;

    @NotEmpty(message = "Email is Required")
    private String email;

    @NotEmpty(message = "Password is Required")
    private String password;

    @NotEmpty(message = "Street is Required")
    private String street;

    @NotEmpty(message = "OutsideNumber is Required")
    private String outsideNumber;

    private String interiorNumber;

    @NotNull(message = "ZipCode is Required")
    private Integer zipCode;

    @NotEmpty(message = "Neighborhood is Required")
    private String neighborhood;

    @NotNull(message = "City is Required")
    private Integer cityId;

    public RegisterNewUserDTO() {
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setOutsideNumber(String outsideNumber) {
        this.outsideNumber = outsideNumber;
    }

    public void setInteriorNumber(String interiorNumber) {
        this.interiorNumber = interiorNumber;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
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

    public String getPassword() {
        return password;
    }

    public String getStreet() {
        return street;
    }

    public String getOutsideNumber() {
        return outsideNumber;
    }

    public String getInteriorNumber() {
        return interiorNumber;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public Integer getCityId() {
        return cityId;
    }
}
