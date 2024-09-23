package com.example.OnlineTicketBooking.service;

import com.example.OnlineTicketBooking.model.User;
import com.example.OnlineTicketBooking.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String email){
        return userRepository.findByUsername(email);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("email not found"));
    }

    public User save(User user) {
        return userRepository.save(user);

    }
}
