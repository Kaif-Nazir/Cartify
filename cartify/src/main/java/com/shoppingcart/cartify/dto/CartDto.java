package com.shoppingcart.cartify.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class CartDto {
    private Long cartId;
    private BigDecimal totalAmount;
    List<CartItemDto> items;
}
