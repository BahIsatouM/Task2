package com.example.OnlineTicketBooking.repository;

import com.example.OnlineTicketBooking.model.Booking;
import com.example.OnlineTicketBooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
}
