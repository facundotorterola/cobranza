package com.uy.cobranza.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Client error")
public class NegocioException  extends  Exception{

    public NegocioException(String message) {
        super(message);
    }
}
