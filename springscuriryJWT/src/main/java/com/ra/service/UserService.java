package com.ra.service;

import com.ra.model.User;
import com.ra.reponse.UserRequestDTO;
import com.ra.reponse.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface UserService{
    User register(User user);
    UserResponseDTO login(UserRequestDTO userRequestDTO);
}
