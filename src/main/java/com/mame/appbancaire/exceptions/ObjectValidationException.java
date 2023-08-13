package com.mame.appbancaire.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.Set;

@RequiredArgsConstructor
public class ObjectValidationException extends RuntimeException {

    @Getter
    private  final Set<String> violations;

    @Getter
    private final String validationSource;

    public String getViolationSource() {
        return null;
    }

    /*public String getViolationSource() {
    }*/
}
