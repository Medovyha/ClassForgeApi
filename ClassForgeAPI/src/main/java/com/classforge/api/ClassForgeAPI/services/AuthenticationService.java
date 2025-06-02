package com.classforge.api.ClassForgeAPI.services;

import com.classforge.api.ClassForgeAPI.dao.User;
import com.classforge.api.ClassForgeAPI.dto.LoginUserDto;
import com.classforge.api.ClassForgeAPI.dto.RegisterUserDto;
import com.classforge.api.ClassForgeAPI.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User user = new User();
                user.setUsername(input.getFullName());
                user.setMail(input.getMail());
                user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getMail(),
                        input.getPassword()
                )
        );

        User user =userRepository.findByMail(input.getMail())
                .orElseThrow();
        return user;
    }
}
