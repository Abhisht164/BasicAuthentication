package com.learning.springsecurity.service;

import com.learning.springsecurity.entity.User;
import com.learning.springsecurity.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    private static final PasswordEncoder encoder=new BCryptPasswordEncoder();

    public void addUserDetails(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info(user.toString());
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).get();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
