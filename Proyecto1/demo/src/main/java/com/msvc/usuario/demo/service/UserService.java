package com.msvc.usuario.demo.service;

import com.msvc.usuario.demo.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getUsers();
    User getUserById(String userId);
}
