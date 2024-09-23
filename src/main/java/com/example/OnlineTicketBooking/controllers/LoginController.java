package com.example.OnlineTicketBooking.controllers;

import com.example.OnlineTicketBooking.model.User;
import com.example.OnlineTicketBooking.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "login"; // return the name of the login HTML template
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        // Implement authentication logic
        User user = userService.findByUsername(username);
        System.out.println(username);
        System.out.println(user);
        if (user != null && user.getPassword().equals(password)) {
            request.getSession().setAttribute("user", user);

            // Role-based redirection logic
            String role = user.getRole(); // Assuming the User model has a getRole method

            if ("ADMIN".equals(role)) {
                return "redirect:/admin/dashboard"; // Redirect to Admin Dashboard
            } else if ("USER".equals(role)) {
                return "redirect:/concerts"; // Redirect to Concerts page for regular users
            } else {
                return "redirect:/home"; // Default redirection
            }
        }
        return "redirect:/login?error"; // redirect back to login with an error
    }

}
