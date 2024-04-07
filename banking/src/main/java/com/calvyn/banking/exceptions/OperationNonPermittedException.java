package com.calvyn.banking.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OperationNonPermittedException extends RuntimeException{
    private final String errorMsg;

    private final String operationId;

    private final String source;

    private final String depecency;
}
