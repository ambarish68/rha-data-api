package com.rha.dataapi.auth.services;

import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.rha.dataapi.hibernate.Privilege;
import com.rha.dataapi.hibernate.Role;
import com.rha.dataapi.hibernate.User;
import com.rha.dataapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
            userDetails = new org.springframework.security.core.userdetails.User(user.get().getEmailId(), user.get().getPassword(), getAuthorities(user.get().getRoles()));
            userCache.put(emailId, userDetails);
            return userDetails;
        }
        return null;
    }

    public void clearCache() {
        userCache.invalidateAll();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
