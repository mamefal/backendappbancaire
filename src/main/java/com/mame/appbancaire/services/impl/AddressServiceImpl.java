package com.mame.appbancaire.services.impl;

import com.mame.appbancaire.dto.AddressDto;
import com.mame.appbancaire.models.Address;
import com.mame.appbancaire.repositories.AddressRepository;
import com.mame.appbancaire.services.AddressService;
import com.mame.appbancaire.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private ObjectsValidator<AddressDto> validator;

    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);
        return repository.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return repository.findAll()
                .stream()
                .map(AddressDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return repository.findById(id)
                .map(AddressDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No address found with the ID: " + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check delete
        repository.deleteById(id);

    }
}
