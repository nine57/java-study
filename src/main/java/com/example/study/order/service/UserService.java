package com.example.study.order.service;

import com.example.study.order.model.request.CreateUserRequest;
import com.example.study.order.model.dto.UserDto;

import java.util.List;

public interface UserService {
    Long createUser(CreateUserRequest request);
    List<UserDto> getAllUsers();
}
