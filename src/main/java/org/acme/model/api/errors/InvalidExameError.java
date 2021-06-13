package org.acme.model.api.errors;

public class InvalidExameError extends ResponseError {
    
    public InvalidExameError(){
        super.code = 526;
        super.errorDescription = "Invalid Exame";
    }
}
