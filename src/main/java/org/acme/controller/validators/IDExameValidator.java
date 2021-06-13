package org.acme.controller.validators;

import javax.inject.Singleton;

import org.acme.model.api.errors.InvalidExameError;

@Singleton
public class IDExameValidator {
    public void validate(Long idExame) throws InvalidExameError{
        if(idExame == null){
            throw new InvalidExameError();
        }
    }
}
