package com.calvyn.banking.validators;


import com.calvyn.banking.exceptions.ObjectValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation; //interface
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory; // interface
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
@Component //je veux que le clycle de vie est gérer par spring bean
public class ObjectsValidator <T>{

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();
    public void validate(T objectToValidate){
        Set<ConstraintViolation <T>> violations = validator.validate(objectToValidate);
        if(!violations.isEmpty()){
            Set<String> errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
           throw new ObjectValidationException(errorMessage,objectToValidate.getClass().getName());
        }

    }
}
