package com.example.OnlineTicketBooking.controllers;

import com.example.OnlineTicketBooking.model.User;
import com.example.OnlineTicketBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup"; // return the name of the signup HTML template
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/login"; // redirect to login after signup
    }

}
