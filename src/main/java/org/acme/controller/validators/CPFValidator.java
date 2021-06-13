package org.acme.controller.validators;



import javax.inject.Singleton;

import org.acme.model.api.errors.InvalidCPFError;

@Singleton
public class CPFValidator {
    public void validate(Long cpf) throws InvalidCPFError{
        if(cpf == null){
            throw new InvalidCPFError();
        }
    }
}
