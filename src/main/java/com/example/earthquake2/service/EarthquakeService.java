package com.example.earthquake2.service;

import com.example.earthquake2.model.ResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Builder
public class EarthquakeService {
    public List<ResponseModel> getResponse() throws JsonProcessingException {
        //RestTemplate in short, it allows us to reach Restful services via java and get a response.
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2022-01-01&endtime=2022-01-02";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        //Send a GET request with getForEntity() and the result type is specified as String.

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode features = root.path("features");
        //root holds all the data given to me, path* to access certain features and parse the data


        List<ResponseModel> responseModels = new ArrayList<>();


        for (int i = 0; i < features.size(); i++) {
            //Since the fields that I will provide to my model from the API are under the features->properties title, that part is accessed and the loop is written.
            ResponseModel properties = ResponseModel.from(features.get(i).path("properties"));
            System.out.println(properties);
            responseModels.add(properties);
            //The properties that I want to attract have been added to the created ResponseModel type list.

        }


        return responseModels;
    }

    //
    public List<ResponseModel> getResponseWithParameter(String country, String StartDate, int countOfDay) {

    //-
        return null;

    }


}
