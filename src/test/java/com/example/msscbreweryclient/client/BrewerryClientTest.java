package com.example.msscbreweryclient.client;

import com.example.msscbreweryclient.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BrewerryClientTest {

    @Autowired
    BrewerryClient brewerryClient;
    @Test
    void getBeerById() {

        BeerDto beerDto= brewerryClient.getBeerById(UUID.randomUUID());
        assertNotNull(beerDto);
    }

    @Test
    void saveBeer() {

        BeerDto beerDto= BeerDto.builder().beerName("Heniken").build();
        URI savedBeerUri = brewerryClient.saveBeer(beerDto);
        assertNotNull(savedBeerUri);
        System.out.println("Saved Beer URI is :"+ savedBeerUri.toString());

    }

    @Test
    void updatedBeer() {
        BeerDto beerDto= BeerDto.builder().beerName("Heniken").build();
        brewerryClient.updatedBeer(UUID.randomUUID(),beerDto);
    }


    @Test
    void deleteBeerById() {
        brewerryClient.deleteBeerById(toString());
    }
}