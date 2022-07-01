package com.jb.currency_exchange.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jb.currency_exchange.exception.CurrencyExchangeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class CurrencyAdvice {
    @ExceptionHandler(value = {CurrencyExchangeException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail handleCurrencyException(Exception e){
        return new ErrorDetail("Currency Exchange Error",e.getMessage());
    }

    @ExceptionHandler(value = {JsonProcessingException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail badJSON(Exception e){
        return new ErrorDetail("JSON Error",e.getMessage());
    }

    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail handleException(Exception e){
        return new ErrorDetail("Error","invalid currency code");
    }
}
