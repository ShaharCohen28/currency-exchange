package com.jb.currency_exchange.clr;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jb.currency_exchange.beans.CurrencyExchangeDetails;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//@Component
@Order(2)
@RequiredArgsConstructor
public class GetExchangeRate implements CommandLineRunner {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    @Override
    public void run(String... args) throws Exception {
        String FROM = "USD";
        String URL = "https://api.exchangerate-api.com/v4/latest/";
        String TO = "ILS";

        String data =restTemplate.getForObject(URL + FROM,String.class);
        double amount=Math.random()*100+1;
        double rate=new JSONObject(data)
                .getJSONObject("rates")
                .getDouble(TO);

        System.out.println("from:"+ FROM);
        System.out.println("to:"+ TO);
        System.out.println("amount:"+amount);
        System.out.println("rate:"+rate);
        System.out.println("result:"+(amount*rate));
        CurrencyExchangeDetails exchange = CurrencyExchangeDetails
                .builder()
                .from(FROM)
                .to(TO)
                .amount(amount)
                .result(amount * rate)
                .build();
        String json=objectMapper.writeValueAsString(exchange);
        System.out.println(json);

    }
}
