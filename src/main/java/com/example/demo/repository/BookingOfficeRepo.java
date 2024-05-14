package com.example.demo.repository;

import com.example.demo.models.BookingOffice;
import com.example.demo.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingOfficeRepo extends JpaRepository<BookingOffice,Long> {
    Optional<BookingOffice> findByName(String name);
}
