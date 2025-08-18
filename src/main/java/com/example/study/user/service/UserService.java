package com.example.study.user.service;

import com.example.study.user.model.dto.UserDto;
import com.example.study.user.model.request.CreateUserRequest;

import java.util.List;

public interface UserService {
    Long createUser(CreateUserRequest request);
    List<UserDto> getAllUsers();
}
