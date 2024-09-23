package com.example.OnlineTicketBooking.repository;

import com.example.OnlineTicketBooking.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository <Concert, Long> {
}
