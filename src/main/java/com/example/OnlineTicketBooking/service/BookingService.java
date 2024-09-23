package com.example.OnlineTicketBooking.service;


import com.example.OnlineTicketBooking.model.Booking;
import com.example.OnlineTicketBooking.model.User;
import com.example.OnlineTicketBooking.repository.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> findByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }

    public void updateBookingStatus(Long id, String status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        booking.setStatus(status);
        bookingRepository.save(booking);
    }

    public void updateBooking(Long id, String status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        booking.setStatus(status);
        bookingRepository.save(booking);
    }




}
