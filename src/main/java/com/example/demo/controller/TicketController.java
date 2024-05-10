package com.example.demo.controller;

import com.example.demo.models.*;
import com.example.demo.repository.BookingOfficeRepo;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.repository.TicketRepo;
import com.example.demo.repository.TripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/ticket")
public class TicketController {
    @Autowired
    private TicketRepo ticketRepo;

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllTicket() {
        List<Ticket> found = ticketRepo.findAll();
        if (found.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "", found)
        );

    }


    @PostMapping("")
    ResponseEntity<ResponseObject> insertTrip(@RequestBody Ticket ticket) {

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Insert successfully", ticketRepo.save(ticket))
        );
    }


}
