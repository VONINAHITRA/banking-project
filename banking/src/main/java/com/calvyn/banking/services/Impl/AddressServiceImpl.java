package com.calvyn.banking.services.Impl;

import com.calvyn.banking.dto.AddressDto;
import com.calvyn.banking.models.Address;
import com.calvyn.banking.repositories.RepositoryAddress;
import com.calvyn.banking.services.AddressService;
import com.calvyn.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private RepositoryAddress repositoryAddress;
    private ObjectsValidator<AddressDto> validator;

    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);
        return repositoryAddress.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return repositoryAddress.findAll()
                .stream()
                .map(AddressDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return repositoryAddress.findById(id)
                .map(AddressDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("No Address found with ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        repositoryAddress.deleteById(id);
    }
}
