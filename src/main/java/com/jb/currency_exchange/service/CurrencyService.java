package com.jb.currency_exchange.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jb.currency_exchange.beans.CurrencyExchangeDetails;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String URL = "https://api.exchangerate-api.com/v4/latest/ILS";
    private Map<String, Double> rates = new HashMap<>();

    public CurrencyExchangeDetails getExchange(String from, String to, double amount) throws JsonProcessingException {
        /*
        String data =restTemplate.getForObject(URL+from,String.class);
        double rate=new JSONObject(data)
                .getJSONObject("rates")
                .getDouble(to);
        return CurrencyExchangeDetails
                .builder()
                .from(from)
                .to(to)
                .amount(amount)
                .result(amount * rate)
                .build();

         */
        double sum = 1;
        //double fromRate=rates.get(from.toUpperCase());
        //double toRate=rates.get(to.toUpperCase());
        //
        if (rates.isEmpty()) {
            getCurr();
        }

        if (from.equalsIgnoreCase("ils") && to.equalsIgnoreCase("ils")) {
            sum = amount;
        } else if (from.equalsIgnoreCase("ils")) {
            sum = amount * rates.get(to.toUpperCase());
        } else if (to.equalsIgnoreCase("ils")) {
            sum = amount / rates.get(from.toUpperCase());
        } else {
            sum = amount * (rates.get(to.toUpperCase()) /  rates.get(from.toUpperCase()));
        }


        //sum=amount*toRate/fromRate;
        return new CurrencyExchangeDetails(from, to, amount, sum);
    }

    @Scheduled(cron = "0 30 06 * * ?")
    public void clearMap() {
        rates.clear();
        ;
    }

    private void getCurr() {
        Map<String, Object> result = restTemplate.getForObject(URL, Map.class);
        rates = (Map<String, Double>) result.get("rates");

    }
}
