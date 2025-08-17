package com.example.study.order.model.dto;

import com.example.study.order.model.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    
    public static UserDto from(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }
}
