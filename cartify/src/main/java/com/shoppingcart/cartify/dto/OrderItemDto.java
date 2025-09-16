package com.shoppingcart.cartify.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {

    private Long productID;
    private String productName;
    private int quantity;
    private BigDecimal price;

}
