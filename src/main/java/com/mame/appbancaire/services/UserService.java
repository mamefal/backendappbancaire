package com.mame.appbancaire.services;

import com.mame.appbancaire.dto.AuthenticationRequest;
import com.mame.appbancaire.dto.AuthenticationResponse;
import com.mame.appbancaire.dto.UserDto;

public interface UserService extends AbstractService<UserDto> {

   Integer validateAccount(Integer id);

   Integer invalidateAccount(Integer id);

    AuthenticationResponse register(UserDto user);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
