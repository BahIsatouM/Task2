package com.example.OnlineTicketBooking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "homepage"; // return the name of the home HTML template
    }

    @GetMapping("/")
    public String homeRoot() {
        return "homepage"; // return the name of the home HTML template
    }
}
