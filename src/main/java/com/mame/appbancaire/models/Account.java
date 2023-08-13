package com.mame.appbancaire.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
public class Account extends AbstractEntity {

    private String iban;


    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

}
