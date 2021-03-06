package com.example.msscbreweryclient.client;

import com.example.msscbreweryclient.model.BeerDto;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery" ,ignoreUnknownFields = false)
public class BrewerryClient {
    public final String BEER_PATH_V1="/api/v1/beer";
    private String apihost;
    private final RestTemplate restTemplate;

    private BrewerryClient(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplate=restTemplateBuilder.build();

    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    public BeerDto getBeerById(UUID id)
    {
        return restTemplate.getForObject(apihost+BEER_PATH_V1+"/"+UUID.randomUUID().toString(),BeerDto.class);
    }

    public URI saveBeer(BeerDto beerDto)
    {
        return restTemplate.postForLocation(apihost+BEER_PATH_V1,beerDto);
    }

    public void updatedBeer(UUID uuid, BeerDto beerDto)
    {
        restTemplate.put(apihost+BEER_PATH_V1+"/"+uuid.toString(),beerDto);
    }
    public void deleteBeerById(String uuid)
    {
        restTemplate.delete(apihost+BEER_PATH_V1+"/"+uuid.toString());
    }
}
