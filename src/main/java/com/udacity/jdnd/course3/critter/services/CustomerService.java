package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer findById(Long id) {
        Optional<Customer> obj = customerRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Customer with ID " +id+ " not found."));
    }
}
