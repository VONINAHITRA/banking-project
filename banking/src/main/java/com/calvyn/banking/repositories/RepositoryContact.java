package com.calvyn.banking.repositories;

import com.calvyn.banking.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryContact extends JpaRepository<Contact, Integer> {
}
