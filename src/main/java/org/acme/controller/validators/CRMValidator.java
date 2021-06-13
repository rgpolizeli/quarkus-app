package org.acme.controller.validators;

import javax.inject.Singleton;

import org.acme.model.api.errors.InvalidCRMError;

@Singleton
public class CRMValidator {
    public void validate(Long crm) throws InvalidCRMError{
        if(crm == null){
            throw new InvalidCRMError();
        }
    }
}
