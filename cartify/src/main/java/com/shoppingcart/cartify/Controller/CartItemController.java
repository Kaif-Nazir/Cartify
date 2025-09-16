package com.shoppingcart.cartify.Controller;


import com.shoppingcart.cartify.service.cart.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cartItem")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/{cartId}/{productId}/{quantity}")
    public ResponseEntity<Void> addItemToCart(@PathVariable Long cartId
                                        , @PathVariable Long productId
                                        , @PathVariable int quantity){
        cartItemService.addItemToCart(cartId,productId,quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cartId}/{productId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long cartId , @PathVariable Long productId){
        cartItemService.removeItemFromCart(cartId , productId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{cartId}/{productId}/{quantity}")
    public ResponseEntity<Void> updateItemQuantity(@PathVariable Long cartId
                                                 , @PathVariable Long productId
                                                 , @PathVariable int quantity){
        cartItemService.updateItemQuantity(cartId,productId,quantity);
        return ResponseEntity.accepted().build();
    }

}
