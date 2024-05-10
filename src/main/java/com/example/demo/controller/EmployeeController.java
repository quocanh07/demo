package com.example.demo.controller;

import com.example.demo.models.Employee;
import com.example.demo.models.ResponseObject;
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
@RequestMapping(path = "/api/v1/employees")

public class EmployeeController {
    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllUserInfo() {
        List<Employee> foundEmployee = employeeRepo.findAll();
        if (foundEmployee.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "", foundEmployee)
        );

    }


    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Employee> foundUserInfo = employeeRepo.findById(id);
        return foundUserInfo.isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "", foundUserInfo)
        ) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("false", "", ""
                ));

    }


    @PostMapping("")
    ResponseEntity<ResponseObject> insertUserInfo(@RequestBody Employee newEmployee) {

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Insert successfully", employeeRepo.save(newEmployee))
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateUserInfo(@RequestBody Employee newUserInfo, @PathVariable Long id) {

       Employee update = employeeRepo.findById(id)
                .map(userInfo -> {
                    userInfo.setUsername(newUserInfo.getUsername());
                    userInfo.setPassword(newUserInfo.getPassword());
                    userInfo.setName(newUserInfo.getName());
                    userInfo.setEmail(newUserInfo.getEmail());
                    userInfo.setPhone(newUserInfo.getPhone());
                    return employeeRepo.save(userInfo);
                }).orElseGet(() -> null);

        if (update!= null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Insert Product successfully", update)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "", "")
        );

    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteUserInfo(@PathVariable Long id) {

        boolean exists = employeeRepo.existsById(id);
        if (exists) {
           employeeRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "", "")
        );
    }
}
