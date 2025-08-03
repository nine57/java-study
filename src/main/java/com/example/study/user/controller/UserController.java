package com.example.study.user.controller;

import com.example.study.user.model.dto.UserDto;
import com.example.study.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 📌 [과제 목표]
 * - 현재 기본 구조만 제공되며, JPA를 활용한 CRUD 기능을 직접 구현해야 합니다.
 * - validation 방식은 각자가 알아서 구현합니다. 구현 안해도 상관 없습니다.
 * - 모든 코드에 정답은 없습니다 JPA Repository를 사용에만 중점을 두고 이외에 모든 방식(정책 or Validation)은 개발자분들의 자유에 맡기겠습니다.
 *
 * ✏️ 구현해야 할 내용:
 *  1. 회원 등록 (createUser)
 *  2. 회원 목록 조회 (getAllUsers)
 *  3. 회원 수정 (updateUser)
 *  4. 회원 이름 수정 (updateUserName)
 *  5. 회원 삭제 (deleteUser)
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userServiceImpl.getUser(id);
    }

    /**
     * TODO 회원 등록 기능 구현
     * - UserDto를 받아 User 엔티티로 변환하여 저장소에 저장
     * - 저장된 회원의 ID를 반환할 것
     */
    @PostMapping
    public Long createUser(@RequestBody UserDto dto) {
        return null;
    }

    /**
     * TODO 회원 전체 조회 기능 구현
     * - 저장된 회원 정보를 전체 반환할 것
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return null;
    }

    /**
     * TODO 회원 정보 수정 기능 구현
     * - UserDto를 받아 기존의 User 정보에 update
     * - update 기준은 각자가 알아서 구현
     * - 응답값은 알아서 작업
     */
    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserDto dto) {
    }

    /**
     * TODO 회원 이름 수정 기능 구현
     * - 변경할 회원 이름을 받아 회원의 이름을 수정합니다.
     * - update 기준은 각자가 알아서 구현
     * - 응답값은 알아서 작업
     */
    @PatchMapping("/{id}")
    public void patchUserName(@PathVariable Long id, @RequestBody String name) {
    }

    /**
     * TODO 회원 삭제 기능 구현
     * - hard delete 기준으로 기능 구현
     * - soft delete 방식으로 구현해보는 것도 추천(선택)
     * - 응답값은 알아서 작업
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
    }
}
