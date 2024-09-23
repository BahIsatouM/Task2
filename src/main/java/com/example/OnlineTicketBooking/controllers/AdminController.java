package com.example.OnlineTicketBooking.controllers;

import com.example.OnlineTicketBooking.model.Booking;
import com.example.OnlineTicketBooking.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/dashboard")
    public String viewDashboard() {
        return "admindashboard"; // View name for GET request
    }

    @GetMapping("/bookings")
    public String viewBookings(Model model) {
        List<Booking> bookings = bookingService.findAll();
        model.addAttribute("bookings", bookings);
        return "bookingmanagement"; // View name for GET request
    }

    @GetMapping("/bookings/edit/{id}")
    public String editBookingForm(@PathVariable Long id, Model model) {
        Booking booking = bookingService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        model.addAttribute("booking", booking);
        return "editbookingpage"; // View name for GET request
    }

    @PostMapping("/bookings/editagain/{id}")
    public String updateBooking(@PathVariable Long id, @RequestParam("status") String status) {
        bookingService.updateBooking(id, status);
        return "redirect:/admin/bookings";
    }

    @GetMapping("/bookings/delete/{id}")
    public String deleteBookingConfirm(@PathVariable Long id, Model model) {
//        Booking booking = bookingService.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        bookingService.deleteById(id);
     //   model.addAttribute("booking", booking);
        return "redirect:/bookings/all"; // View name for GET request
    }

    @PostMapping("/bookings/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteById(id);
        return "redirect:/admin/bookings";
    }
}
