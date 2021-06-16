package com.uy.cobranza.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<Object> handleNegocioException(BusinessException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException responseException = new ApiException(e.getMessage(),e, badRequest, ZonedDateTime.now(ZoneId.of("Z")));
        return  new ResponseEntity<>(responseException,badRequest);
    }
}
