package com.devsuperior.bds02.exceptions.error;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError{

    List<FieldMessage> errs = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessage> getErrs() {
        return errs;
    }

    public void addError(String fielName, String message){
            errs.add(new FieldMessage(fielName, message));
    }
}
