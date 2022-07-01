package com.jb.currency_exchange.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jb.currency_exchange.exception.CurrencyExchangeException;
import com.jb.currency_exchange.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/money")
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping("/convert/{from}/{to}/{amount}")
    public ResponseEntity<?> getExchange(@PathVariable String from,@PathVariable String to,@PathVariable double amount) throws JsonProcessingException,NullPointerException{
        return new ResponseEntity<>(currencyService.getExchange(from, to, amount),HttpStatus.OK);
    }



}
