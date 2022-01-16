package nus.edu.workshop17.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class CurrencyService {
    
    private static final String CURRENCY_API = System.getenv("currencyAPI");
    private static final String CURRENCY_URL = "https://free.currconv.com//api/v7/currencies";

    public List<String> getAllCurrency() {
        List<String> currenyList = new ArrayList<>();

        String url = UriComponentsBuilder
                    .fromUriString(CURRENCY_URL)
                    .queryParam("apiKey", CURRENCY_API)
                    .toUriString();
        RequestEntity<Void> req = RequestEntity.get(url).build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);

        if(resp.getStatusCode() != HttpStatus.OK) {
            throw new IllegalArgumentException("Error: status code %d".format(resp.getStatusCode().toString()));
        }

        String body = resp.getBody();

        try(InputStream is = new ByteArrayInputStream(body.getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonObject result = reader.readObject();
            JsonObject resultCurrency = result.getJsonObject("results");

/*             for(String key : resultCurrency.keySet()) {
                JsonObject keyValue = (JsonObject) resultCurrency.get(key);
                currenyList.add(keyValue.get("currencyName").toString());
            } */

            for(String key : resultCurrency.keySet()) {
                currenyList.add(key);
            }
            
        } catch(Exception e) {}

        return currenyList;
    }

}
