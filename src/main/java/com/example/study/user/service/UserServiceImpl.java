package com.example.study.user.service;

import com.example.study.user.exception.UserValidationException;
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
        validateCreateUser(dto);

        User user = new User(null, dto.getEmail(), dto.getPassword(), dto.getName());
        User savedUser = userRepository.save(user);
        log.info("새로운 사용자가 생성되었습니다. ID: {}", savedUser.getId());
        return savedUser.getId();
    }

    private void validateCreateUser(UserDto dto) {
        if (dto == null) {
            throw new UserValidationException("사용자 정보가 없습니다.");
        }

        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            throw new UserValidationException("이메일은 필수 입력값입니다.");
        }

        if (!dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new UserValidationException("올바른 이메일 형식이 아닙니다.");
        }

        if (dto.getPassword() == null || dto.getPassword().length() < 8) {
            throw new UserValidationException("비밀번호는 8자 이상이어야 합니다.");
        }

        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new UserValidationException("이름은 필수 입력값입니다.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDto::of)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateUser(Long id, UserDto dto) {
        validateUpdateUser(id, dto);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + id));

        user.updateAll(dto.getEmail(), dto.getPassword(), dto.getName());
        log.info("사용자 정보가 수정되었습니다. ID: {}", id);
    }

    private void validateUpdateUser(Long id, UserDto dto) {
        if (id == null) {
            throw new UserValidationException("사용자 ID가 없습니다.");
        }

        if (dto == null) {
            throw new UserValidationException("수정할 사용자 정보가 없습니다.");
        }

        if (dto.getEmail() != null && !dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new UserValidationException("올바른 이메일 형식이 아닙니다.");
        }

        if (dto.getPassword() != null && dto.getPassword().length() < 8) {
            throw new UserValidationException("비밀번호는 8자 이상이어야 합니다.");
        }
    }

    @Override
    @Transactional
    public void patchUserName(Long id, String name) {
        validatePatchUserName(id, name);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다: " + id));

        user.updateName(name);
        log.info("사용자 이름이 수정되었습니다. ID: {}, 새로운 이름: {}", id, name);
    }

    private void validatePatchUserName(Long id, String name) {
        if (id == null) {
            throw new UserValidationException("사용자 ID가 없습니다.");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new UserValidationException("이름은 필수 입력값입니다.");
        }

        if (name.length() > 50) {
            throw new UserValidationException("이름은 50자를 초과할 수 없습니다.");
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다: " + id);
        }
        userRepository.deleteById(id);
        log.info("사용자가 삭제되었습니다. ID: {}", id);
    }
}