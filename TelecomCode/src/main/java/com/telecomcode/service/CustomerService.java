package com.telecomcode.service;

import com.telecomcode.model.Customer;
import com.telecomcode.model.Phone;
import com.telecomcode.repository.CustomerRepository;
import com.telecomcode.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PhoneRepository phoneRepository) {
        this.customerRepository = customerRepository;
        this.phoneRepository = phoneRepository;
    }

    public ResponseEntity<Customer> createCustomer(Customer customer) {
        try {
            Customer newCustomer = customerRepository.save(customer);
            return new ResponseEntity<>(newCustomer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<String> getAllPhoneNumbers() {
        try {
            List<String> phoneNum = new ArrayList<>();
            List<Phone> ph = phoneRepository.findAll();
            for(Phone phNum : ph) {
                phoneNum.add(phNum.getPhoneNumber());
            }
            return phoneNum;
        } catch (Exception e) {
            return null;
        }
    }

    public List<String> getPhoneNumbersByCustomerName(@PathVariable("customerName") String customerName) {
        //check if customer exist in database
        try {
            Customer customerObj = getCustomerByName(customerName);
            List<String> phNum = new ArrayList<>();
            if (customerObj != null && customerObj.getCustomerName().equals(customerName)) {
                for(Phone ph : customerObj.getPhone()) {
                    phNum.add(ph.getPhoneNumber());
                }
                return phNum;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public ResponseEntity<Phone> activateCustomerPhone(@PathVariable("phoneNumber") String phoneNumber) {

        try{
        //check if phone exist in database
        Phone phoneObj  = getPhoneRecByNumber(phoneNumber);
        String phNumber = phoneObj.getPhoneNumber();
        if(phNumber.equals(phoneNumber)) {
            phoneRepository.updateByIsActivateTrue(phoneNumber);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Customer getCustomerByName(String customerName) {
        Customer customerObj = customerRepository.findByCustomerName(customerName);
        if (customerObj != null) {
            return customerObj;
        }
        return null;
    }

    private Phone getPhoneRecByNumber(String phoneNumber) {
        Phone phoneObj = phoneRepository.findByPhoneNumber(phoneNumber);
        if (phoneObj != null) {
            return phoneObj;
        }
        return null;
    }


}
