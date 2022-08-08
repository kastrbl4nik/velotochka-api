package com.example.velotochka.exceptions;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(Long id) {
        super(String.format("No category with id=%d.", id));
    }

    public CategoryNotFoundException(String name) {
        super(String.format("No category with name=%s.", name));
    }
}
