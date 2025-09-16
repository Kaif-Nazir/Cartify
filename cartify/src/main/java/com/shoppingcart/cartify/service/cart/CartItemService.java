package com.shoppingcart.cartify.service.cart;


import com.shoppingcart.cartify.exception.ItemNotFoundException;
import com.shoppingcart.cartify.exception.ResourceNotFoundException;
import com.shoppingcart.cartify.exception.UserNotFoundException;
import com.shoppingcart.cartify.model.Cart;
import com.shoppingcart.cartify.model.CartItem;
import com.shoppingcart.cartify.model.Product;
import com.shoppingcart.cartify.model.User;
import com.shoppingcart.cartify.repository.CartItemRepository;
import com.shoppingcart.cartify.repository.CartRepository;
import com.shoppingcart.cartify.repository.UserRepository;
import com.shoppingcart.cartify.service.product.IProductService;
import com.shoppingcart.cartify.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CartItemService implements ICartItemService{

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final IProductService productService;
    private final ICartService cartService;

    @Override
    @Transactional
    public void addItemToCart(Long cartId, Long productId, int quantity) {
        // Get Cart
        // Get Product
        // Check if Product already in cart
        // If Yes Update Quantity
        // if No add new CartItem

        Cart cart = cartService.getCart(cartId);
        Product product = productService.getProductById(productId);
        CartItem cartItem = cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElse(new CartItem());
        if (cartItem.getId() == null) {
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());
            cartItem.setTotalPrice();
            cart.addItem(cartItem);
        }
        else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.setTotalPrice();
        }
        cartItemRepository.save(cartItem);
        cart.updateTotalAmount();
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void removeItemFromCart(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        CartItem itemToRemove = getCartItem(cartId, productId);
        if(itemToRemove == null)
            throw new ItemNotFoundException("Item Not Found With This Id: " + productId);
        cart.removeItem(itemToRemove);
        cartRepository.save(cart);
    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {

        Cart cart = cartService.getCart(cartId);
        Set<CartItem> cartItemsSet = cart.getItems();
        CartItem cartItem = null; // To Store Item

        for(CartItem currItem : cartItemsSet){
            if(currItem.getProduct().getId().equals(productId)){
                cartItem = currItem;
                break;
            }
        }
        if(cartItem != null){
            cartItem.setQuantity(quantity);
            cartItem.setTotalPrice();
            cart.setTotalAmount(cart.getTotalAmount());
            cartRepository.save(cart);
        }else{
            throw new ItemNotFoundException("Item Not Found");
        }
    }
    @Override
    public CartItem getCartItem(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        return  cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Item not found"));
    }
}
