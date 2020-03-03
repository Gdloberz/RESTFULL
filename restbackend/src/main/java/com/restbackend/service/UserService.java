package com.restbackend.service;

import com.restbackend.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    boolean addUser(User usero);

    void deleteUser(User usero);

    User getUserById(Long id);

    public void updateUser(User updateUser);

    User getUserByEmail(String email);
}
