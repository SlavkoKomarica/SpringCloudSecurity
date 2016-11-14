package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by slavko.komarica on 11/14/2016.
 */
@Configuration
@EnableResourceServer
@RestController
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @RequestMapping({"/user", "/me"})
    public Principal user(Principal principal) {
        return principal;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/me").authorizeRequests().anyRequest().authenticated();
    }
}
