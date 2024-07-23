package com.example.accessingdatajpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
//@Table(name = "users") // Optional: Specify the table name if it differs from the class name
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false, unique = true)
    private String name;
    
    private String brand;

//    @Column(nullable = false)
    private String category;

    // Other user attributes can be defined here
    // For example:
    // @Column(nullable = false)
    // private String email;

    // Constructors, getters, and setters

    public Product() {
        // Default constructor
    }

    public Product(String name, String category) {
        this.name = name;
        this.category = category;
        // Initialize other attributes as needed
    }
    
    public Product(String name, String category, String brand) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        // Initialize other attributes as needed
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
