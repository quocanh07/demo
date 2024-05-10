package com.example.demo.repository;

import com.example.demo.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;


public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    Optional<Employee> findByUsername(String username);
    Optional<Employee> findById(long id);



}
