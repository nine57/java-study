package com.example.study.user.service;

import com.example.study.user.model.domain.User;
import com.example.study.user.model.dto.UserDto;
import com.example.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return UserDto.of(user);
    }

    @Override
    @Transactional
    public Long createUser(UserDto dto) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    @Transactional
    public void updateUser(Long id, UserDto dto) {}

    @Override
    @Transactional
    public void patchUserName(Long id, String name) {}

    @Override
    @Transactional
    public void deleteUser(Long id) {}
}
