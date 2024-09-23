package com.example.OnlineTicketBooking.controllers;


import com.example.OnlineTicketBooking.model.Booking;
import com.example.OnlineTicketBooking.model.Concert;
import com.example.OnlineTicketBooking.model.User;
import com.example.OnlineTicketBooking.service.BookingService;
import com.example.OnlineTicketBooking.service.ConcertService;
import com.example.OnlineTicketBooking.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ConcertService concertService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @GetMapping("/concerts")
    public String viewConcerts(Model model) {
        List<Concert> concerts = concertService.findAll();
        model.addAttribute("concerts", concerts);
        return "concertlistpage";
    }

    @GetMapping("/concert/{id}")
    public String viewConcertDetails(@PathVariable Long id, Model model) {
        Concert concert = concertService.findById(id).orElseThrow(() -> new EntityNotFoundException("Concert not found"));
        model.addAttribute("concert", concert);
        return "concertDetails";
    }


    @PostMapping("/book-concert/{id}")
    public String bookConcert(@PathVariable Long id, Principal principal) {
        Concert concert = concertService.findById(id).orElseThrow(() -> new EntityNotFoundException("Concert not found"));
        User user = userService.findByUsername(principal.getName());
        Booking booking = new Booking();
        booking.setConcert(concert);
        booking.setUser(user);
        booking.setStatus("PENDING");
        bookingService.save(booking);
        return "redirect:userbookings/concertlist";
    }

    @GetMapping("/my-bookings")
    public String viewMyBookings(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Booking> bookings = bookingService.findByUser(user);
        model.addAttribute("bookings", bookings);
        return "userbookings/bookingpage";
    }

    @GetMapping("/edit-booking/{id}")
    public String editBooking(@PathVariable Long id, Model model) {
        Booking booking = bookingService.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        model.addAttribute("booking", booking);
        return "userbookings/editbookingpage";
    }

    @PostMapping("/edit-booking/{id}")
    public String updateBooking(@PathVariable Long id, @ModelAttribute Booking booking) {
        Booking existingBooking = bookingService.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        existingBooking.setConcert(booking.getConcert());
        existingBooking.setStatus(booking.getStatus());
        bookingService.save(existingBooking);
        return "redirect:/userbookings/bookingpage";
    }

    @GetMapping("/delete-booking/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteById(id);
        return "redirect:/userbookings/bookingpage";
    }
}
