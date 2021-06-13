package org.acme.model.api.errors;

public class InvalidConvenioError extends ResponseError  {
    public InvalidConvenioError(){
        super.code = 528;
        super.errorDescription = "Invalid Convenio";
    }
}
