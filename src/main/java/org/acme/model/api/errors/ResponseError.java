package org.acme.model.api.errors;

public class ResponseError extends Throwable{
    public Integer code;
    public String errorDescription;
}
