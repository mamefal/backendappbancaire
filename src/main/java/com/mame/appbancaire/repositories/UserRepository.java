package com.mame.appbancaire.repositories;

import com.mame.appbancaire.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    //select * from User where firstname ='mame'
    List<User> findAllByFirstname(String firstname);

    Optional<User> findByEmail(String email);
}
