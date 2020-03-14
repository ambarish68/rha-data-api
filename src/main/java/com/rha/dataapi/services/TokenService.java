package com.rha.dataapi.services;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.rha.dataapi.config.JwtTokenUtil;
import com.rha.dataapi.hibernate.AccessToken;
import com.rha.dataapi.hibernate.User;
import com.rha.dataapi.repositories.TokenRepository;
import com.rha.dataapi.repositories.UserRepository;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {
    /**
     * Since there is no redis infra yet, we will maintain the token and user details in the cache.
     */
    private Cache<String, User> tokenUserCache;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    void buildCache() {
        tokenUserCache = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.DAYS).maximumSize(50).recordStats().build();
    }

    public void persistTokenInDatabase(String token, UserDetails userDetails) {
        AccessToken accessToken = new AccessToken();
        DateTime dateTime = new DateTime(DateTimeZone.UTC);
        Optional<User> user = userRepository.findByEmailId(userDetails.getUsername());
        accessToken.setCreatedAt(dateTime.toDate());
        accessToken.setUser(user.get());
        accessToken.setExpiresAt(dateTime.toDateTime().plusSeconds((int) JwtTokenUtil.JWT_TOKEN_VALIDITY).toDate());
        tokenRepository.save(accessToken);
        tokenUserCache.put(token, user.get());
    }
}
