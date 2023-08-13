package com.mame.appbancaire.repositories;

import com.mame.appbancaire.models.Account;
import com.mame.appbancaire.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository  extends JpaRepository<Address, Integer> {
}
