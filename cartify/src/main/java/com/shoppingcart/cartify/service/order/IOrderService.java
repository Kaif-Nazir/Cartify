package com.shoppingcart.cartify.service.order;

import com.shoppingcart.cartify.dto.OrderDto;
import com.shoppingcart.cartify.model.Orders;

import java.util.List;

public interface IOrderService {

    OrderDto getOrder (Long orderId);
    OrderDto placeOrder(Long userId);
    List<OrderDto> getUserOrders(Long userId);
    OrderDto convertToDto(Orders order);
    List<OrderDto> getConvertedDto(List<Orders> order);

}
