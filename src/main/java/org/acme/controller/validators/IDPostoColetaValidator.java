package org.acme.controller.validators;

import javax.inject.Singleton;

import org.acme.model.api.errors.InvalidPostoColetaError;

@Singleton
public class IDPostoColetaValidator {
    public void validate(Long idPostoColeta) throws InvalidPostoColetaError{
        if(idPostoColeta == null){
            throw new InvalidPostoColetaError();
        }
    }
}
