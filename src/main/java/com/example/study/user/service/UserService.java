package com.example.study.user.service;

import com.example.study.user.model.dto.UserDto;

import java.util.List;

public interface UserService {
    // 회원 등록
    Long createUser(UserDto dto);
    
    // 회원 목록 조회
    List<UserDto> getAllUsers();
    
    // 회원 수정
    void updateUser(Long id, UserDto dto);
    
    // 회원 이름 수정
    void patchUserName(Long id, String name);
    
    // 회원 삭제
    void deleteUser(Long id);
    
    // 특정 회원 조회
    UserDto getUser(Long id);
}
