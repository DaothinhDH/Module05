package com.ra.service.user;

import com.ra.exception.CustomException;
import com.ra.model.dto.request.UserRequest;
import com.ra.model.dto.response.UserResponse;
import com.ra.model.entity.User;
import com.ra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(UserResponse::new);
    }

    @Override
    public UserResponse saveOrUpdate(UserRequest userRequest) throws CustomException {
                // check trung
                if(userRepository.existsByUsername(userRequest.getUsername())) {
                    throw new CustomException("UserName existed");
                }
        User user = User.builder()
                .username(userRequest.getUsername()).
                fullName(userRequest.getFullName()).
                password(userRequest.getPassword()).build();
        User userNew = userRepository.save(user);

        return UserResponse.builder().id(userNew.getId()).userName(userNew.getUsername()).fullName(userRequest.getFullName()).build();
    }

    @Override
    public UserResponse findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
           return UserResponse.builder().userName(user.getUsername()).fullName(user.getFullName()).build();
        }
        return null;
    }
}
