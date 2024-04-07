package com.calvyn.banking.services.Impl;

import com.calvyn.banking.dto.ContactDto;
import com.calvyn.banking.models.Contact;
import com.calvyn.banking.repositories.RepositoryContact;
import com.calvyn.banking.services.ContactService;
import com.calvyn.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    private RepositoryContact repositoryContact;

    private ObjectsValidator<ContactDto> validator;

    @Override
    public Integer save(ContactDto dto) {
        validator.validate(dto);
        Contact contact = ContactDto.toEntity(dto);
        return repositoryContact.save(contact).getId();
    }

    @Override
    public List<ContactDto> findAll() {
        return repositoryContact.findAll()
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Integer id) {
        return repositoryContact.findById(id)
                .map(ContactDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("No Contact was found with the ID :" + id));
    }

    @Override
    public void delete(Integer id) {
     repositoryContact.deleteById(id);
    }
}
