package com.example.books.dto;

import java.io.Serializable;
import java.util.Date;

public class BookDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String title;

    private String imageURL;

    private String author;

    private Date publishedDate;
}
