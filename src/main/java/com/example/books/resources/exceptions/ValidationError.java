package com.example.books.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private static final long serialVersionUID = 1L;

    public List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String message, Long timesTamp) {
        super(status, message, timesTamp);
    }

    public List<FieldMessage> getList() {
        return errors;
    }

    public void addError(String fieldMessage, String message) {
        errors.add(new FieldMessage(fieldMessage, message));
    }
}
