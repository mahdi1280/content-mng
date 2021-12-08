package com.gd.contentmng.controller;

import com.gd.core.ErrorMessage;
import com.gd.core.RuleException;
import com.gd.core.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(RuleException.class)
    public ResponseEntity<List<ErrorMessage>> ruleError(RuleException ruleException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ruleException.getErrorMessages());
    }

    //ConstraintViolationException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<ValidationError> validationErrors = new ArrayList<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            ValidationError validationError = ValidationError.builder()
                    .setCode(fieldError.getCode())
                    .setDefaultMessage(fieldError.getDefaultMessage())
                    .setField(fieldError.getObjectName())
                    .setRejectedValue(String.valueOf(fieldError.getRejectedValue()))
                    .build();
            validationErrors.add(validationError);

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {
        LOGGER.error("an exception occurred during processing request.", e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

}
