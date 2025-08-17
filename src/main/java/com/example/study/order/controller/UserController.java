package com.example.study.order.controller;

import com.example.study.common.model.GlobalResponse;
import com.example.study.order.model.request.CreateUserRequest;
import com.example.study.order.model.dto.UserDto;
import com.example.study.order.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
    @PostMapping
    public GlobalResponse<Long> createUser(@Valid @RequestBody CreateUserRequest request) {
        Long userId = userService.createUser(request);
        return GlobalResponse.success(userId);
    }
    
    @GetMapping
    public GlobalResponse<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return GlobalResponse.success(users);
    }
}
