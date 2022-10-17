package com.example.earthquake2.controller;

import com.example.earthquake2.model.ResponseModel;
import com.example.earthquake2.service.EarthquakeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EarthquakeController {

    private final EarthquakeService earthquakeService;


    @GetMapping
    public List<ResponseModel> getResponse() throws JsonProcessingException {
        return earthquakeService.getResponse();
    }
    @GetMapping
    public  List<ResponseModel> getResponseWithParameter(@RequestParam String country,@RequestParam String startDate, @RequestParam int count){
        return earthquakeService.getResponseWithParameter(country,startDate,count);
    }

}
