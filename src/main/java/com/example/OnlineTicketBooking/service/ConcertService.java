package com.example.OnlineTicketBooking.service;

import com.example.OnlineTicketBooking.model.Concert;
import com.example.OnlineTicketBooking.repository.ConcertRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConcertService {
    @Autowired
    private ConcertRepository concertRepository;

    public List<Concert> findAll(){
        return concertRepository.findAll();
    }

    public Optional<Concert> findById(Long id){
        return concertRepository.findById(id);
    }


    public Concert save(Concert concert){
        return concertRepository.save(concert);
    }

    public Concert updateConcert(Long id, Concert updatedConcert){
        Concert concert = concertRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Concert not found"));
        concert.setName(updatedConcert.getName());
        concert.setLocation(updatedConcert.getLocation());
        concert.setDateTime(updatedConcert.getDateTime());
        return concertRepository.save(concert);
    }

    public void deleteConcert(Long id) {
        concertRepository.deleteById(id);
    }
}

