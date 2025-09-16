package com.shoppingcart.cartify.controller;


import com.shoppingcart.cartify.dto.CartDto;
import com.shoppingcart.cartify.model.Cart;
import com.shoppingcart.cartify.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // Get cart by ID
    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCart(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.convertToDto(cartService.getCart(id)));
    }


    // Clear cart by ID
    @DeleteMapping("/{id}/clear")
    public ResponseEntity<Cart> clearCart(@PathVariable Long id) {
        Cart cart =  cartService.clearCart(id);
        return ResponseEntity.ok(cart);
    }

    // Get total price of cart
    @GetMapping("/{id}/total")
    public ResponseEntity<BigDecimal> getTotalPrice(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.getTotalPrice(id));
    }
}