package org.acme.model.api.errors;

public class InvalidCPFError extends ResponseError  {
    public InvalidCPFError(){
        super.code = 524;
        super.message = "Invalid CPF";
    }
}
