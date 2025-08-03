package com.example.study.user.model.dto;

import com.example.study.user.model.domain.User;
import lombok.*;

/**
 * @author : 변영우 byw1666@wjcompass.com
 * @since : 2025-07-28
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String name;

    public static UserDto of(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getPassword(), user.getName());
    }
}
