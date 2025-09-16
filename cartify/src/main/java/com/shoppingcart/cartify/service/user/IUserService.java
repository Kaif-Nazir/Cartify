package com.shoppingcart.cartify.service.user;

import com.shoppingcart.cartify.dto.UserDto;
import com.shoppingcart.cartify.model.User;
import com.shoppingcart.cartify.request.CreateUserRequest;
import com.shoppingcart.cartify.request.UpdateUserRequest;

public interface IUserService {

    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UpdateUserRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertToDto(User user);
}
