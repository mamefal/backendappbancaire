package com.mame.appbancaire.services.impl;


import com.mame.appbancaire.dto.ContactDto;
import com.mame.appbancaire.models.Contact;
import com.mame.appbancaire.repositories.ContactRepository;
import com.mame.appbancaire.services.ContactService;
import com.mame.appbancaire.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository repository;
    private final ObjectsValidator<ContactDto> validator;

    @Override
    public Integer save(ContactDto dto) {
        validator.validate(dto);
        Contact contact = ContactDto.toEntity(dto);
        return repository.save(contact).getId();
    }

    @Override
    public List<ContactDto> findAll() {
        return repository.findAll()
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Integer id) {
        return repository.findById(id)
                .map(ContactDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("no contact was found with the ID :" + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check delete
        repository.deleteById(id);

    }

    @Override
    public List<ContactDto> findAllByUserId(Integer userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }
}
