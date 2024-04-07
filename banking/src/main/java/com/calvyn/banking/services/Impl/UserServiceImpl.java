package com.calvyn.banking.services.Impl;

import com.calvyn.banking.dto.UserDto;
import com.calvyn.banking.models.User;
import com.calvyn.banking.repositories.UserRepository;
import com.calvyn.banking.services.UserService;
import com.calvyn.banking.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//avec la nouvelle version de spirng plus d'utiliser @Autorired
@Service
@RequiredArgsConstructor //lombok crer le constructeur
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ObjectsValidator<UserDto> validator;

    @Override
    public Integer save(UserDto dto) {
           validator.validate(dto); //verification la validation
           User user = UserDto.toEntity(dto); //transforme le data
        return userRepository.save(user).getId(); //sauv agerder et retourner id
    }

    @Override
    public List<UserDto> findAll() { //liste === stream()
        return userRepository.findAll()
                .stream()//valeur retour vient de base de données //for list
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id)
                .map(UserDto::fromEntity) //direct map car un seul objet
                .orElseThrow(()-> new EntityNotFoundException("No user was dound with the provided ID" + id));
    }

    @Override
    public void delete(Integer id) {
       //todo check before delete
        userRepository.deleteById(id);
    }
}
