package com.jb.currency_exchange.clr;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//@Component
@Order(1)
@RequiredArgsConstructor
public class GetPojo implements CommandLineRunner {
    private final RestTemplate restTemplate;
    private final String url="https://api.exchangerate-api.com/v4/latest/ILS";
    @Override
    public void run(String... args) throws Exception {
        String result=restTemplate.getForObject(url,String.class);
        System.out.println(result);

    }
}
