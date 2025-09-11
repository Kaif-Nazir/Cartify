package com.shoppingcart.cartify.request;

import com.shoppingcart.cartify.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {

    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;

}
