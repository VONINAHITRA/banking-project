package com.calvyn.banking.repositories;

import com.calvyn.banking.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAddress extends JpaRepository<Address, Integer> {
}
