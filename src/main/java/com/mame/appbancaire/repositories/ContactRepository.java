package com.mame.appbancaire.repositories;

import com.mame.appbancaire.dto.ContactDto;
import com.mame.appbancaire.models.Address;
import com.mame.appbancaire.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findByUserId(Integer userId);
}

