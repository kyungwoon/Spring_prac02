package com.example.spring_prac02.repository;

import com.example.spring_prac02.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickname(String username);

    Optional<User> findByKakaoId(Long KakaoId);

    Optional<User> findByEmail(String mail);
}
