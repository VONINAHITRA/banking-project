package com.calvyn.banking.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;
@RequiredArgsConstructor
public class ObjectValidationException extends RuntimeException{ //faut etendre exception, catch l'exption

    @Getter
    private final Set<String> violations;

    @Getter
    private final String violationSource;



}
