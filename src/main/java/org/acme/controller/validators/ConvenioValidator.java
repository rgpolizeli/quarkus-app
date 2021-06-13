package org.acme.controller.validators;

import javax.inject.Singleton;

import org.acme.model.api.errors.InvalidConvenioError;

@Singleton
public class ConvenioValidator {
    public void validate(String convenio) throws InvalidConvenioError{
        if(convenio.isEmpty()){
            throw new InvalidConvenioError();
        }
    }
}
