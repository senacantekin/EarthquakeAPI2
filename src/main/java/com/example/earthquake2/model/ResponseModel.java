package com.example.earthquake2.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.Date;

@Data
@Builder
public class ResponseModel {

    private String country;
    private String place;
    private long mag;
    private Date date;


    public static ResponseModel from(JsonNode node) {
        String[] places = node.get("place").asText().split(",");

        String country = "not exist country name";
        if (places.length > 1) {
            country = places[1];
        }

        // The @builder annotation from the lombok library is used to define a model. Thus, the builder design pattern was complied with.
        // If we want to create an object with a minimum parameter set and to keep these fields as they were created, we take advantage of the builder design pattern.
        return ResponseModel.builder()

                .country(country)
                .place(node.get("place").asText())
                .mag(node.get("mag").asLong())
                .date(new Date(Long.parseLong(node.get("time").asText())))
                .build();


    }


    //
    //
}



