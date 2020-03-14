package com.rha.dataapi.auth.services;

import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.rha.dataapi.hibernate.User;
import com.rha.dataapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    private Cache<String, UserDetails> userCache;

    @PostConstruct
    void buildCache() {
        userCache = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.DAYS).maximumSize(50).recordStats().build();
    }

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        Preconditions.checkNotNull(emailId, "emailId cannot be null");
        UserDetails userDetails = userCache.getIfPresent(emailId);
        if (userDetails != null) {
            return userDetails;
        }
        Optional<User> user = userRepository.findByEmailId(emailId);
        if (user.isPresent()) {
            userDetails = new org.springframework.security.core.userdetails.User(user.get().getEmailId(), user.get().getPassword(), Lists.newArrayList());
            userCache.put(emailId, userDetails);
            return userDetails;
        }
        return null;
    }

    public void clearCache(){
        userCache.invalidateAll();
    }
}
