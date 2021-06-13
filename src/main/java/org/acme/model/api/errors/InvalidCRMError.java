package org.acme.model.api.errors;

public class InvalidCRMError extends ResponseError {
    
    public InvalidCRMError(){
        super.code = 525;
        super.errorDescription = "Invalid CRM";
    }
}
