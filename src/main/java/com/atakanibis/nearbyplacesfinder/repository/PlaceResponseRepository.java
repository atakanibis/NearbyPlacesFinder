package com.atakanibis.nearbyplacesfinder.repository;

import com.atakanibis.nearbyplacesfinder.model.PlaceResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceResponseRepository extends JpaRepository<PlaceResponse, Long> {

    PlaceResponse findByLatitudeAndLongitudeAndRadius(String latitude, String longitude, String radius);
}
