package com.example.accessingdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.accessingdatajpa.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Method to find a user by username
    User findByUsername(String username);

    // Other custom queries can be defined here if needed
}
