package com.ra.service;

import com.ra.model.Role;
import com.ra.model.User;
import com.ra.reponse.UserRequestDTO;
import com.ra.reponse.UserResponseDTO;
import com.ra.repository.UserRepository;
import com.ra.security.jwt.JWTProvider;
import com.ra.security.user_principle.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private RoleService roleService;
    @Override
    public User register(User user) {
        // ma hoa mat khau
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // role
        Set<Role> roles = new HashSet<>();
        // register cua user thi coi no la user
        if (user.getRoles() == null || user.getRoles().isEmpty()){
            roles.add(roleService.findByRoleName("USER"));
        }else {
            // tạo tk và phân quyền thì phải có quyền admin
            user.getRoles().forEach(role -> {
                roles.add(roleService.findByRoleName(role.getName()));
            });
        }
       User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setStatus(user.isStatus());
        newUser.setRoles(roles);
        userRepository.save(user);
        return userRepository.save(user);
    }

    @Override
    public UserResponseDTO login(UserRequestDTO userRequestDTO) {
        Authentication authentication;
        authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(userRequestDTO.getUserName(),userRequestDTO.getPassword()));
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return UserResponseDTO.builder().token(jwtProvider.generateToken(userPrinciple)).
                username(userPrinciple.getUsername()).
                roles(userPrinciple.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet())).
                build();
    }
}
