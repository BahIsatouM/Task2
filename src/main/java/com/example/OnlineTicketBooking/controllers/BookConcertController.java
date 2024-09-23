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
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookConcertController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ConcertService concertService;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String listUserBookings(Model model) {
//        User user = userService.findByUsername(principal.getName());
        List<Booking> bookings = bookingService.findAll();
        model.addAttribute("bookings", bookings);
        return "bookingmanagement";
    }

    @GetMapping("/create/{concertId}")
    public String createBookingForm(@PathVariable Long concertId, Model model) {
        Concert concert = concertService.findById(concertId).orElseThrow(() -> new EntityNotFoundException("Concert not found"));
        model.addAttribute("concert", concert);
        model.addAttribute("booking", new Booking());
        return "createbooking";//create booking page
    }

    @PostMapping("/create/{concertId}")
    public String createBooking(@PathVariable Long concertId, @ModelAttribute Booking booking, Principal principal) {
        Concert concert = concertService.findById(concertId).orElseThrow(() -> new EntityNotFoundException("Concert not found"));
//        User user = userService.findByUsername(principal.getName());
        booking.setConcert(concert);
//        booking.setUser(user);
        booking.setStatus("PENDING");
        bookingService.save(booking);
        return "redirect:/bookings/confirmation";
    }

    @GetMapping("/confirmation")
    public String bookingConfirmation(Model model) {
        // This view will show the booking confirmation message
        return "booking-confirmation";
    }

    @GetMapping("/concert/{id}")
    public String concertDetails(@PathVariable Long id, Model model) {
        Concert concert = concertService.findById(id).orElseThrow(() -> new EntityNotFoundException("Concert not found"));
        model.addAttribute("concert", concert);
        return "concert-details";
    }

    @GetMapping("/edit/{id}")
    public String editBookingForm(@PathVariable Long id, Model model) {
        Booking booking = bookingService.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        model.addAttribute("booking", booking);
        return "userbookings/editbooking";
    }

    @PostMapping("/edit/{id}")
    public String updateBooking(@PathVariable Long id, @ModelAttribute Booking booking) {
        Booking existingBooking = bookingService.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        existingBooking.setConcert(booking.getConcert());
        existingBooking.setStatus(booking.getStatus());
        bookingService.save(existingBooking);
        return "redirect:/bookings/all";
    }

    @PostMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteById(id);
        return "redirect:/bookings/all";
    }

}
