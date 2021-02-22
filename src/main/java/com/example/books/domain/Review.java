package com.example.books.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private ReviewPK id = new ReviewPK();

    private String reviewContent;

    private Date publishedDate;

    public Review() {
    }

    public Review(User user, Book book, String reviewContent, Date publishedDate) {
        id.setUser(user);
        id.setBook(book);
        this.reviewContent = reviewContent;
        this.publishedDate = publishedDate;
    }

    public void setUser(User user) {
        id.setUser(user);
    }

    public void setBook(Book book) {
        id.setBook(book);
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    @JsonIgnore
    public User getUser() {
        return id.getUser();
    }

    public Book getBook() {
        return id.getBook();
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Review other = (Review) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
