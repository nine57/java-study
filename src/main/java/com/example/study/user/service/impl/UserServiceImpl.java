package com.example.study.user.service.impl;

import com.example.study.user.model.domain.User;
import com.example.study.user.model.dto.UserDto;
import com.example.study.user.model.request.CreateUserRequest;
import com.example.study.user.repository.UserRepository;
import com.example.study.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    
    @Override
    @Transactional
    public Long createUser(CreateUserRequest request) {
        User user = new User(request.getName(), request.getEmail());
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}
