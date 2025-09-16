package com.shoppingcart.cartify.service.cart;

import com.shoppingcart.cartify.model.Cart;

import java.math.BigDecimal;

public interface ICartService {

    Cart getCart(Long id);
    Cart clearCart(Long id);
    BigDecimal getTotalPrice(Long id);
    Cart getCartByUserId(Long id);

}
