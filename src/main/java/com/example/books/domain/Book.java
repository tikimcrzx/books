package com.example.books.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String imageURL;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date publishedDate;

    @Column(nullable = false)
    private String isbn;

    private Integer quantity;

    public Book() {
        this.quantity = 1;
    }

    public Book(Integer id, String title, String imageURL, String author, Date publishedDate, String isbn) {
        this.id = id;
        this.title = title;
        this.imageURL = imageURL;
        this.author = author;
        this.publishedDate = publishedDate;
        this.isbn = isbn;
        this.quantity = 1;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void addQuantity() {
        quantity++;
    }

    public void subtractQuantity() {
        quantity--;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
