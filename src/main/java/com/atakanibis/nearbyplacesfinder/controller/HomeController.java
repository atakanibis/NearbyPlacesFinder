package com.atakanibis.nearbyplacesfinder.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Value("${google.places.api.key}")
    private String googleApiKey;
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("googleApiKey", googleApiKey);
        return "index";
    }
}
