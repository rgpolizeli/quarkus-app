package org.acme.model.api.errors;

public class InvalidPostoColetaError extends ResponseError {
    
    public InvalidPostoColetaError(){
        super.code = 527;
        super.message = "Invalid PostoColeta";
    }
}
