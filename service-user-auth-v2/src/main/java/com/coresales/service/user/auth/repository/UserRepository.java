package com.coresales.service.user.auth.repository;

import com.coresales.service.user.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository //-> no es necesario poner @Repository porque ya está heredando de JpaRepository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
