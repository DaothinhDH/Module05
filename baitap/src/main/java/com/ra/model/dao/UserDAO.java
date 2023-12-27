package com.ra.model.dao;

import com.ra.model.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();
    boolean saveOfUpdate(User user);
    void delete(Integer id);
    User findById(Integer id);
}
