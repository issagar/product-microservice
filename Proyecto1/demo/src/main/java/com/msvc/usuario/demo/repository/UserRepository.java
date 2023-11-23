package com.msvc.usuario.demo.repository;

import com.msvc.usuario.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
