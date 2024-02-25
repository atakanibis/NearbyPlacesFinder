package com.atakanibis.nearbyplacesfinder.service;

import com.atakanibis.nearbyplacesfinder.repository.PlaceResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.atakanibis.nearbyplacesfinder.model.PlaceResponse;

@Service
public class GooglePlacesAPIService {

    private static final String PLACES_API_BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";

    @Value("${google.places.api.key}")
    private String apiKey;

    @Autowired
    private PlaceResponseRepository placeResponseRepository;

    public String searchNearbyPlaces(String latitude, String longitude, String radius) {
        PlaceResponse placeResponse;

        // recurring request control
        placeResponse = placeResponseRepository.findByLatitudeAndLongitudeAndRadius(latitude, longitude, radius);
        if (placeResponse != null && placeResponse.getResponse() != null) {
            return placeResponse.getResponse();
        }


        String url = PLACES_API_BASE_URL + "location=" + latitude + "," + longitude +
                "&radius=" + radius + "&key=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);


        // Saving response to database
        if (placeResponse != null){
            placeResponse.setLatitude(latitude);
            placeResponse.setLongitude(longitude);
            placeResponse.setRadius(radius);
            placeResponse.setResponse(response);
            placeResponseRepository.save(placeResponse);
        }

        return response;
    }
}
