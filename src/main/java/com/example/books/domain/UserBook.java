package com.example.books.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
public class UserBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private UserBookPK id = new UserBookPK();

    private Date checkoutDate;

    private Date returnDate;

    public UserBook() {
    }

    public UserBook(User user, Book book, Date checkoutDate, Date returnDate) {
        id.setUser(user);
        id.setBook(book);
        this.checkoutDate = checkoutDate;
        this.returnDate = returnDate;
    }

    public void setUser(User user) {
        id.setUser(user);
    }

    public void setBook(Book book) {
        id.setBook(book);
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @JsonIgnore
    public User getUser() {
        return id.getUser();
    }

    public Book getBook() {
        return id.getBook();
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public Date getReturnDate() {
        return returnDate;
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
        UserBook other = (UserBook) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
