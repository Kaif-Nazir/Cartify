package com.shoppingcart.cartify.request;

import com.shoppingcart.cartify.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductRequest {

    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category; // nested DTO
}
