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

import com.example.accessingdatajpa.entity.Product;
import com.example.accessingdatajpa.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
//@RequestMapping("/customer")
public class ProductController {
	
//	@Autowired
//	UserService userService;
	
	@Autowired
	ProductRepository repository;
	
	@PutMapping("/product")
	public ResponseEntity<Product> create(@RequestBody String payload) {
		Product result = null;
		try {
            ObjectMapper objectMapper = new ObjectMapper();
            Product product = objectMapper.readValue(payload, Product.class);

            System.out.println(product); // Output the converted Java object
//            return null;
            result = repository.save(product);
            
            return new ResponseEntity<Product>(
            		result, 
                    HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return new ResponseEntity<Product>(
				result, 
                HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> results = new ArrayList<Product>();
		
		repository.findAll().forEach(results::add);
		
		return new ResponseEntity<>(
				results, 
                HttpStatus.OK);
	}
	
	@GetMapping("/getAllCategories")
	public ResponseEntity<Set<String>> getAllCategories() {
//		List<Product> results = new ArrayList<Product>();
		Set<String> resultSet = new HashSet<String>();
		
		repository.findAll().forEach(product -> {
			if(!product.getCategory().isEmpty()) {
				resultSet.add(product.getCategory());
			}
		});
		
		return new ResponseEntity<>(
				resultSet, 
                HttpStatus.OK);
	}
	
	
}