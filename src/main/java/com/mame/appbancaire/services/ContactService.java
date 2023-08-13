package com.mame.appbancaire.services;

import com.mame.appbancaire.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto> {
    List<ContactDto> findAllByUserId(Integer userId);
}
