package com.shoppingcart.cartify.Controller;


import com.shoppingcart.cartify.dto.OrderDto;
import com.shoppingcart.cartify.service.order.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {

    private final IOrderService orderService;

    @GetMapping("/{orderId}")
     public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId){
        return ResponseEntity.ok((orderService.getOrder(orderId)));
    }
    @GetMapping("/allOrders/{id}")
     public ResponseEntity<List<OrderDto>> getAllOrder(@PathVariable Long id){
        return ResponseEntity.ok((orderService.getUserOrders(id)));
    }
    @PostMapping("/{userId}")
    public ResponseEntity<OrderDto> placeOrder(@PathVariable Long userId){
        return ResponseEntity.ok((orderService.placeOrder(userId)));
    }
}
