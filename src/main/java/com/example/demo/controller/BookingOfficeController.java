package com.example.demo.controller;


import com.example.demo.models.BookingOffice;
import com.example.demo.models.Employee;
import com.example.demo.models.ResponseObject;
import com.example.demo.repository.BookingOfficeRepo;
import com.example.demo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/booking-office")
public class BookingOfficeController {
    @Autowired
    private BookingOfficeRepo bookingOfficeRepo;

    @GetMapping("")
    ResponseEntity<ResponseObject> getAll() {
        List<BookingOffice> found = bookingOfficeRepo.findAll();
        if (found.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "", found)
        );

    }


    @GetMapping("/{name}")
    ResponseEntity<ResponseObject> findByName(@PathVariable String name) {
        Optional<BookingOffice> found = bookingOfficeRepo.findByName(name);
        return found.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "", found)
        ) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("false", "", ""
                ));

    }


    @PostMapping("")
    ResponseEntity<ResponseObject> insertUserInfo(@RequestBody BookingOffice newBooking) {

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Insert successfully", bookingOfficeRepo.save(newBooking))
        );
    }




}
