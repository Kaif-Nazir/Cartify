package com.shoppingcart.cartify.service.order;

import com.shoppingcart.cartify.dto.OrderDto;
import com.shoppingcart.cartify.dto.ProductDto;
import com.shoppingcart.cartify.enums.OrderStatus;
import com.shoppingcart.cartify.exception.ItemNotFoundException;
import com.shoppingcart.cartify.exception.ResourceNotFoundException;
import com.shoppingcart.cartify.model.Cart;
import com.shoppingcart.cartify.model.Orders;
import com.shoppingcart.cartify.model.OrderItem;
import com.shoppingcart.cartify.model.Product;
import com.shoppingcart.cartify.repository.OrderRepository;
import com.shoppingcart.cartify.repository.ProductRepository;
import com.shoppingcart.cartify.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ICartService iCartService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public OrderDto placeOrder(Long userId) {

        Cart cart = iCartService.getCartByUserId(userId);
        if(cart.getItems().isEmpty()){
            throw new ItemNotFoundException("Cart Is Empty");
        }
        Orders orders = createOrder(cart);
        // Orders Does Not Have OrderItem and Total Amount Now
        Set<OrderItem> orderItemsList = createOrderItem(orders, cart);
        orders.setOrderItems(new HashSet<>(orderItemsList));
        orders.setTotalAmount(calculateTotalAmount(orderItemsList));
        orders.setOrderStatus(OrderStatus.PROCESSED);
        orders.setOrderDate(LocalDate.now());
        Orders savedOrders = orderRepository.save(orders);
        iCartService.clearCart(cart.getId());
        return convertToDto(savedOrders);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDto getOrder(Long orderId) {
        return convertToDto(orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order Not Found")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getUserOrders(Long userId){
        return getConvertedDto(orderRepository.findByUserId(userId));
    }

    @Override
    public OrderDto convertToDto(Orders order){
        return modelMapper.map(order , OrderDto.class);
    }

    @Override
    public List<OrderDto> getConvertedDto(List<Orders> orders) {
        return orders.stream().map(this::convertToDto).toList();
    }
    // Helper For Placing Orders

    private Set<OrderItem> createOrderItem(Orders orders, Cart cart){

        return cart.getItems().stream().map(cartItem ->{
            Product product = cartItem.getProduct();
            if(product.getInventory() < cartItem.getQuantity()){
                throw new IllegalStateException("Not Enough Quantity For : "+ product.getName());
            }
            product.setInventory(product.getInventory() - cartItem.getQuantity());
            productRepository.save(product);
            return new OrderItem(
                    orders,
                    product,
                    cartItem.getQuantity(),
                    cartItem.getUnitPrice()
            );
        }).collect(Collectors.toSet());
    }

    private Orders createOrder(Cart cart){

        Orders orders = new Orders();
        orders.setUser(cart.getUser());
        orders.setOrderStatus(OrderStatus.PENDING);
        orders.setOrderDate(LocalDate.now());
        return orders;

    }

    private BigDecimal calculateTotalAmount(Set<OrderItem> orderItemList){
        return orderItemList
                .stream()
                .map(item -> item.getPrice()
                               .multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO , BigDecimal::add);
    }



}
