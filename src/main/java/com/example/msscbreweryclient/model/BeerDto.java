package com.example.msscbreweryclient.model;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class BeerDto {

    private UUID id;
    private String beerName;
    private String beerStyle;
    private Long upc;

    public enum BeerStyleEnum {
        ale,lager,stout,porter,bitter,paleale
    }
}
