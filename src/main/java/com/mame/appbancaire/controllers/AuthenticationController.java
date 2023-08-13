package com.mame.appbancaire.controllers;

import com.mame.appbancaire.config.JwtUtils;
import com.mame.appbancaire.dto.AuthenticationRequest;
import com.mame.appbancaire.dto.AuthenticationResponse;
import com.mame.appbancaire.dto.UserDto;
import com.mame.appbancaire.repositories.UserRepository;
import com.mame.appbancaire.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserDto user) {

       return ResponseEntity.ok(userService.register(user));

    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(userService.authenticate(request));


    }
}
