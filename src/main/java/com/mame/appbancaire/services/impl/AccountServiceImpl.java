package com.mame.appbancaire.services.impl;

import com.mame.appbancaire.dto.AccountDto;
import com.mame.appbancaire.exceptions.OperationNonPermittedException;
import com.mame.appbancaire.models.Account;
import com.mame.appbancaire.repositories.AccountRepository;
import com.mame.appbancaire.services.AccountService;
import com.mame.appbancaire.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final ObjectsValidator<AccountDto> validator;

    @Override
    public Integer save(AccountDto dto) {

        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);
        boolean userHasAlreadyAnAccount = repository.findAllByUserId(account.getUser().getId()).isPresent();
        if (userHasAlreadyAnAccount && account.getUser().isActive()) {
            throw new OperationNonPermittedException(
                    "the selected user has already an active account",
                    "Create account",
                    "Account service",
                    "Account creation"
            );
        }
        // generate random IBAN when creating new account else do not update the IBAN
        if (dto.getId() == null) {
            account.setIban(generateRandomIban());
    }
        return repository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return repository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No account was found with the ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check delete account
        repository.deleteById(id);

    }

    private String generateRandomIban() {
        // generate an iban
        String iban = Iban.random(CountryCode.DE).toFormattedString();

        // check if the iban already exists
        boolean ibanExists = repository.findByIban(iban).isEmpty();
        // if exists -> generate new random iban
        if (ibanExists) {
            generateRandomIban();
        }
        // if not exists -> return generate iban
        return  iban;
    }
}
