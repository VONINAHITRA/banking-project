package com.calvyn.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Calvyn
 * @since 15.03.2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder //si on une héritage avec Lombok , pour contructe d'autre hértité
@Entity
@Table(name = "_user")
public class User extends AbstractEntity{ //JPQL on manipule objet pas la table, on utilise le non d'entite pas le nom de la table.

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private boolean active;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;

    @OneToOne
    private Account account;

    @OneToOne
    private Role role;
}
