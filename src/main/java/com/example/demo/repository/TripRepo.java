package com.example.demo.repository;

import com.example.demo.models.BookingOffice;
import com.example.demo.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepo extends JpaRepository<Trip,Long> {
}
