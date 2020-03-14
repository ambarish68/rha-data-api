package com.rha.dataapi.cache.controllers;

import com.rha.dataapi.auth.services.JwtUserDetailsService;
import com.rha.dataapi.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cache")
public class CacheController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @DeleteMapping("/{cacheType}")
    public void clearCache(@PathVariable String cacheType) {
        if ("user".equalsIgnoreCase(cacheType)) {
            jwtUserDetailsService.clearCache();
        } else if ("token".equalsIgnoreCase(cacheType)) {
            tokenService.clearCache();
        }
    }
}
