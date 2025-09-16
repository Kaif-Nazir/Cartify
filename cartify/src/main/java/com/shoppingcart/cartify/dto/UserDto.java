package com.shoppingcart.cartify.dto;

import com.shoppingcart.cartify.model.Cart;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private CartDto cart;



}
