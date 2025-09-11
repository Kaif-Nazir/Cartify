package com.shoppingcart.cartify.exception;


public class CategoryAlreadyExist extends RuntimeException {

    public CategoryAlreadyExist(String error){
        super(error);
    }

}
