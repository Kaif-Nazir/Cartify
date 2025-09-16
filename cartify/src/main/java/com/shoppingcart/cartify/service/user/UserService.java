package com.shoppingcart.cartify.service.user;

import com.shoppingcart.cartify.dto.UserDto;
import com.shoppingcart.cartify.exception.UserNotFoundException;
import com.shoppingcart.cartify.model.Cart;
import com.shoppingcart.cartify.model.User;
import com.shoppingcart.cartify.repository.CartRepository;
import com.shoppingcart.cartify.repository.UserRepository;
import com.shoppingcart.cartify.request.CreateUserRequest;
import com.shoppingcart.cartify.request.UpdateUserRequest;
import com.shoppingcart.cartify.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Not Found With id :" + userId));
    }

    @Override
    public User createUser(CreateUserRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            throw new IllegalStateException("User Already Exist By This Email :" + request.getEmail());
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);
        userRepository.save(user);
        cartRepository.save(cart);
        return user;

    }

    @Override
    public User updateUser(UpdateUserRequest request, Long userId) {
        return userRepository.findById(userId).map(existingUser -> {
            existingUser.setFirstName(request.getFirstName());
            existingUser.setLastName(request.getLastName());
            return userRepository.save(existingUser);
        })
            .orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User Not Found To Delete");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto convertToDto(User user){
        return modelMapper.map(user , UserDto.class);
    }
}
