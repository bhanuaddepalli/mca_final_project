
package com.video.streaming.exceptions;


import com.video.streaming.data.APIExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class GlobalExceptionHandler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

   

    /**
     * OPAPI common exception.
     *
     * @param exp the exp
     * @return the response entity
     */
    @ExceptionHandler(value = APIException.class)
    public ResponseEntity<APIExceptionResponse> OPAPICommonException(APIException exp) {
        APIExceptionResponse response = new APIExceptionResponse(new Date(System.currentTimeMillis()),
                exp.getStatus().value(), exp.getStatus().name(), exp.getMessage());
        return new ResponseEntity<>(response, exp.getStatus());
    }

    /**
     * Generic exception.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> GenericException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String> > handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}