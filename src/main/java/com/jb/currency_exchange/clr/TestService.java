package com.jb.currency_exchange.clr;

import com.jb.currency_exchange.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@Order(3)
@RequiredArgsConstructor
public class TestService implements CommandLineRunner {
    private final CurrencyService currencyService;
    @Override
    public void run(String... args) throws Exception {
        System.out.println(currencyService.getExchange("USD","ILS",100));

    }
}
