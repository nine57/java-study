package com.example.study.user.repository;

import com.example.study.user.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : 변영우 byw1666@wjcompass.com
 * @since : 2025-07-28
 */

public interface UserRepository extends JpaRepository<User, Long> {
}
