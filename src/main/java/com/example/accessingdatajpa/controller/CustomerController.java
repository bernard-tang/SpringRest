package com.example.accessingdatajpa.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
//import org.springframework.data.repository.CrudRepository;

//import com.example.demo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.accessingdatajpa.entity.Customer;
import com.example.accessingdatajpa.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
//@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerRepository repository;

	@PutMapping("/customer")
	public ResponseEntity<Customer> create(@RequestBody String payload) {
		Customer result = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Customer customer = objectMapper.readValue(payload, Customer.class);

			System.out.println(customer); // Output the converted Java object
			result = repository.save(customer);

			return new ResponseEntity<Customer>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Customer>(result, HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> results = new ArrayList<Customer>();

		repository.findAll().forEach(results::add);

		return new ResponseEntity<>(results, HttpStatus.OK);
	}

}
