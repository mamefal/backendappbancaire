package com.mame.appbancaire.repositories;

import com.mame.appbancaire.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

  Optional<Account> findByIban(String iban);
  Optional<Account> findAllByUserId(Integer id);


}
