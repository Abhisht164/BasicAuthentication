package com.learning.springsecurity.service;

import com.learning.springsecurity.config.UserDetailsUserInfo;
import com.learning.springsecurity.entity.User;
import com.learning.springsecurity.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByName(username);
        log.info(user.get().toString());
        return user.map(UserDetailsUserInfo::new)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"+username));
    }
}
