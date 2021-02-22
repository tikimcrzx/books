package com.example.books.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer status;
    private String message;
    private Long timesTamp;

    public StandardError(Integer status, String message, Long timesTamp) {
        this.status = status;
        this.message = message;
        this.timesTamp = timesTamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Long getTimesTamp() {
        return timesTamp;
    }
}
