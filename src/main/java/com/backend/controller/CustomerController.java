
package com.backend.controller;

import com.backend.dto.CustomerDto;
import com.backend.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<CustomerDto>> getCustomersByStatus(@RequestParam boolean active) {
        return ResponseEntity.ok(customerService.getActiveCustomers(active));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}