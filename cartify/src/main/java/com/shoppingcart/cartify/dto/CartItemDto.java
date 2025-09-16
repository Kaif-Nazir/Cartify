package com.shoppingcart.cartify.dto;


import com.shoppingcart.cartify.model.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {

    private Long itemId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private Product product;

}
