package com.mame.appbancaire.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
public class Role extends AbstractEntity {

    private  String name;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;
}
