package com.shoppingcart.cartify.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message){
        super(message);
    }
}
