package com.mame.appbancaire.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    @Column(
            name= "createDate" ,
            nullable = false,
            updatable = false
    )
    private LocalDateTime creationDate;

    @LastModifiedDate
    @Column(name = "lastModifiedDate")
    private LocalDateTime lastUpdated;
}
