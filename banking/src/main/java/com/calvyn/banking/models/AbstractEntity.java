package com.calvyn.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass //pour dire que c'est une class mapped pour heritage à partir d'autres entites
@EntityListeners(AuditingEntityListener.class) // pour activer ou calculer les valeur de CreatedDate et LastModifiedDate (activer sur poitn d'entre d'app (main ))
public class AbstractEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate //mettre à jour le date
    @Column(
            name = "createdDate",
            nullable = false, //ne doit pas être null
            updatable = false // je veux que la date ne soit pas modifier, c'est la date de création
    )
    private LocalDateTime creationDate;

    @LastModifiedDate //dernièr modification , ne sera modifier lorsque je fait un mise à jours.
    @Column(name = "lastModifiedDate")
    private LocalDateTime lastModifiedDate;
}
