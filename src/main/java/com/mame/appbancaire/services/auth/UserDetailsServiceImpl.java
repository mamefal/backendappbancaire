package com.mame.appbancaire.services.auth;

import com.mame.appbancaire.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email ) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("No user was found with the provided email"));
    }
}
