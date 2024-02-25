package com.atakanibis.nearbyplacesfinder.controller;

import com.atakanibis.nearbyplacesfinder.service.GooglePlacesAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @Autowired
    private GooglePlacesAPIService googlePlacesAPIService;

    @GetMapping("/api/places")
    public String searchPlaces(@RequestParam("longitude") String longitude,
                               @RequestParam("latitude") String latitude,
                               @RequestParam("radius") String radius) {

        return googlePlacesAPIService.searchNearbyPlaces(longitude, latitude, radius);
    }
}
