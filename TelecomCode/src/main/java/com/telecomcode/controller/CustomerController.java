package com.telecomcode.controller;

import com.telecomcode.model.Customer;
import com.telecomcode.model.Phone;
import com.telecomcode.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Create new customer
     *
     * @param customer
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<Customer> newCustomer(@Valid @RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    /**
     * Get all phone numbers
     *
     * @return ResponseEntity
     */
    @GetMapping("/phoneNumbers")
    public List<String> getAllCustomersPhoneNumbers() {
        return customerService.getAllPhoneNumbers();
    }

    /**
     * Get all phone numbers of a single customer
     *
     * @param customerName
     * @return list
     */
    @GetMapping("/{customerName}/phoneNumbers")
    public List<String> getPhoneNumbersByCustomer(@PathVariable("customerName") String customerName) {
            return customerService.getPhoneNumbersByCustomerName(customerName);
    }

    /**
     * Activate customer phone number by using phone number
     *
     * @param phoneNumber
     * @return phone
     */
    @PatchMapping("/phoneNumbers/{phoneNumber}")
    public ResponseEntity<Phone> activateCustomerPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        return customerService.activateCustomerPhone(phoneNumber);
    }

}
