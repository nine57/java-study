package com.example.study.user.service;

import com.example.study.user.model.dto.UserDto;

import java.util.List;

/**
 * @author : 변영우 byw1666@wjcompass.com
 * @since : 2025-07-28
 */
public interface UserService {

    /**
     * 사용자 ID로 단일 사용자 정보를 조회합니다.
     */
    UserDto getUser(Long id);

    /**
     * 새로운 사용자를 생성합니다.
     */
    Long createUser(UserDto dto);

    /**
     * 모든 사용자 정보를 조회합니다.
     */
    List<UserDto> getAllUsers();

    /**
     * 사용자 정보를 전체 수정합니다.
     */
    void updateUser(Long id, UserDto dto);

    /**
     * 사용자의 이름만 수정합니다.
     */
    void patchUserName(Long id, String name);

    /**
     * 사용자를 삭제합니다.
     */
    void deleteUser(Long id);
}
