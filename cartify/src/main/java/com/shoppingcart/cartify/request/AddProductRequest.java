package com.shoppingcart.cartify.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductRequest {

    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private CategoryRequest category; // nested DTO

    @Data
    public static class CategoryRequest {
        private String name;
    }
}
