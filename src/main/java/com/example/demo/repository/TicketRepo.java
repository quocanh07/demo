package com.example.demo.repository;

import com.example.demo.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket,Long> {
}
