package com.bilgeadam.OBS.with.spring.boot.Configuration;

import com.bilgeadam.OBS.with.spring.boot.Entity.User;
import com.bilgeadam.OBS.with.spring.boot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyUserProvider implements AuthenticationProvider {
    UserRepository repository;

    @Autowired
    public MyUserProvider(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<GrantedAuthority> roles = new ArrayList<>();
        User user = repository.findUserByEmail(email);
        if (user != null) {
            roles.add(new SimpleGrantedAuthority(user.getRole()));
            return new UsernamePasswordAuthenticationToken(email, password, roles);
        } else {
            throw new BadCredentialsException("Invalid User Details.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
