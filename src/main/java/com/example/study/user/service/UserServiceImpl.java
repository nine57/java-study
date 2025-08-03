package com.example.study.user.service;

import com.example.study.user.model.domain.User;
import com.example.study.user.model.dto.UserDto;
import com.example.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : 변영우 byw1666@wjcompass.com
 * @since : 2025-07-28
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        return UserDto.of(user);
    }

    @Override
    @Transactional
    public Long createUser(UserDto dto) {
        User user = new User(null, dto.getEmail(), dto.getPassword(), dto.getName());
        User savedUser = userRepository.save(user);
        log.info("User created with ID: {}", savedUser.getId());
        return savedUser.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDto::of)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateUser(Long id, UserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        
        user.updateAll(dto.getEmail(), dto.getPassword(), dto.getName());
        log.info("User updated with ID: {}", id);
    }

    @Override
    @Transactional
    public void patchUserName(Long id, String name) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        
        user.updateName(name);
        log.info("User name updated with ID: {} to name: {}", id, name);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
        log.info("User deleted with ID: {}", id);
    }
}
