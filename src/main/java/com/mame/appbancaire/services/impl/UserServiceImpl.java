package com.mame.appbancaire.services.impl;

import com.mame.appbancaire.config.JwtUtils;
import com.mame.appbancaire.dto.AccountDto;
import com.mame.appbancaire.dto.AuthenticationRequest;
import com.mame.appbancaire.dto.AuthenticationResponse;
import com.mame.appbancaire.dto.UserDto;
import com.mame.appbancaire.models.Role;
import com.mame.appbancaire.models.User;
import com.mame.appbancaire.repositories.RoleRepository;
import com.mame.appbancaire.repositories.UserRepository;
import com.mame.appbancaire.services.AccountService;
import com.mame.appbancaire.services.UserService;
import com.mame.appbancaire.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String ROLE_USER = "ROLE_USER";
    private final RoleRepository roleRepository;
    private final UserRepository repository;
    private final AccountService accountService;
    private final ObjectsValidator<UserDto> validator;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final UserRepository userRepository;

    private final AuthenticationManager authManager;


    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user).getId();
    }

    @Override
    public List<UserDto> findAll() {
        return repository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }
    @Override
    public UserDto findById(Integer id) {
        return repository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No user was found with the provided ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        //todo check before delete
        repository.deleteById(id);

    }

    @Override
    @Transactional
    public Integer validateAccount(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for user account validation"));
        //create a bank account
        AccountDto account = AccountDto.builder()
                .user(UserDto.fromEntity(user))
                .build();
        accountService.save(account);

        user.setActive(true);
        repository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user was found for user account invalidation"));
        user.setActive(false);
        repository.save(user);
        return user.getId();
    }

    @Override
    @Transactional
    public AuthenticationResponse register(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(
                findOrCreateRole(ROLE_USER)

        );
        var savedUser = repository.save(user);
        String token = jwtUtils.generateToken(savedUser);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails user = repository.findByEmail(request.getEmail()).get();
        final String token = jwtUtils.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private Role findOrCreateRole(String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElse(null);
        if (role == null) {
            return roleRepository.save(
                    Role.builder()
                            .name(roleName)
                            .build()
            );
        }
            return role;
        }
    }

