package com.rha.dataapi.auth.services;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.rha.dataapi.hibernate.User;
import com.rha.dataapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        Preconditions.checkNotNull(emailId, "emailId cannot be null");
        Optional<User> user = userRepository.findByEmailId(emailId);
        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(user.get().getEmailId(),user.get().getPassword(), Lists.newArrayList());
        }
        return null;
    }
}
