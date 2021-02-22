package com.example.books.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String outsideNumber;

    private String interiorNumber;

    @Column(nullable = false)
    private Integer zipCode;

    @Column(nullable = false)
    private String neighborhood;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Address() {
    }

    public Address(Integer id, String street, String outsideNumber, String interiorNumber, Integer zipCode,
                   String neighborhood, User user, City city) {
        this.id = id;
        this.street = street;
        this.outsideNumber = outsideNumber;
        this.interiorNumber = interiorNumber;
        this.zipCode = zipCode;
        this.neighborhood = neighborhood;
        this.user = user;
        this.city = city;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public City getCity() {
        return city;
    }
}
