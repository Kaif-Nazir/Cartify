package com.shoppingcart.cartify.repository;

import com.shoppingcart.cartify.model.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @EntityGraph(attributePaths = {"items"})
    Optional<Cart> findById(Long id);
    Cart getCartByUserId(Long id);
}
