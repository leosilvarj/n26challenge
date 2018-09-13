package com.n26.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DateConvertException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 6191308510617328925L;

    public DateConvertException(String message) {
        super(message);
    }

}
