package com.example.demo.controller;

import com.example.demo.models.BookingOffice;
import com.example.demo.models.Employee;
import com.example.demo.models.ResponseObject;
import com.example.demo.models.Trip;
import com.example.demo.repository.BookingOfficeRepo;
import com.example.demo.repository.EmployeeRepo;
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
@RequestMapping(path = "/api/v1/trip")
public class TripController {
    @Autowired
    private TripRepo tripRepo;

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllTrip() {
        List<Trip> foundTrip = tripRepo.findAll();
        if (foundTrip.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "", foundTrip)
        );

    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Trip> found = tripRepo.findById(id);
        return found.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "", found)
        ) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("false", "", ""
                ));

    }
    @PostMapping("")
    ResponseEntity<ResponseObject> insertTrip(@RequestBody Trip trip) {

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Insert successfully", tripRepo.save(trip))
        );
    }


}
