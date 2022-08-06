package com.example.velotochka.exceptions;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(Long id) {
        super(String.format("No product with id=%d.", id));
    }
}
