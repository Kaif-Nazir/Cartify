package com.shoppingcart.cartify.Controller;


import com.shoppingcart.cartify.dto.UserDto;
import com.shoppingcart.cartify.model.User;
import com.shoppingcart.cartify.request.CreateUserRequest;
import com.shoppingcart.cartify.request.UpdateUserRequest;
import com.shoppingcart.cartify.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.convertToDto(userService.getUserById(id)));
    }

    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest request){
        return ResponseEntity
                .ok(userService.convertToDto(userService.createUser(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id , @RequestBody UpdateUserRequest request){
        return ResponseEntity
                .ok(userService.convertToDto(userService.updateUser(request , id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
