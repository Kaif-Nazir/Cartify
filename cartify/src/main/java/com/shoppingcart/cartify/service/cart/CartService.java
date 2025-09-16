package com.shoppingcart.cartify.service.cart;

import com.shoppingcart.cartify.dto.CartDto;
import com.shoppingcart.cartify.exception.ResourceNotFoundException;
import com.shoppingcart.cartify.exception.UserNotFoundException;
import com.shoppingcart.cartify.model.Cart;
import com.shoppingcart.cartify.repository.CartItemRepository;
import com.shoppingcart.cartify.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService implements  ICartService{

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;

    @Override
    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart clearCart(Long id) {
        Cart cart = getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        cart.getItems().clear();
        cart.updateTotalAmount();
        return cartRepository.save(cart);
    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart = getCart(id);
        return cart.getTotalAmount();
    }

    @Override
    @Transactional(readOnly = true)
    public Cart getCartByUserId(Long id) {
        return Optional.ofNullable(cartRepository.getCartByUserId(id))
                .orElseThrow(() -> new UserNotFoundException("User Not Found With This Id : " + id));
    }

    public CartDto convertToDto(Cart cart){
        return modelMapper.map(cart , CartDto.class);
    }

}
