package com.devsuperior.bds02.exceptions.error;

public class FieldMessage {

    private String fieldError;
    private String message;

    public FieldMessage(){

    }

    public FieldMessage(String fieldError, String message) {
        this.fieldError = fieldError;
        this.message = message;
    }

    public String getFieldError() {
        return fieldError;
    }

    public String getMessage() {
        return message;
    }
}
