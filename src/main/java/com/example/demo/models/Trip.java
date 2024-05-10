package com.example.demo.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trip")
@Builder
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripId;
    private int bookedTicketNumber;
    private String carType;
    private Date departureDate;
    private String departureTime;
    private String destination;
    private String driver;
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<BookingOffice> bookingOffices;

}
