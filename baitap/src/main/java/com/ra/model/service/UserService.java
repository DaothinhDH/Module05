package com.ra.model.service;

import com.ra.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    boolean saveOfUpdate(User user);
    void delete(Integer id);
    User findById(Integer id);
}
