package com.example.accessingdatajpa;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import com.example.accessingdatajpa.repository.CustomerRepository;
import com.example.accessingdatajpa.repository.UserRepository;
import com.example.accessingdatajpa.entity.Customer;
import com.example.accessingdatajpa.entity.User;

@SpringBootApplication
public class AccessingDataJpaApplication extends SpringBootServletInitializer{

	private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			repository.findAll().forEach(customer -> {
				log.info(customer.toString());
			});
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			log.info("");
		};
	}
	
	@Bean
	public CommandLineRunner userRepo(UserRepository repository) {
		return (args) -> {
			List<String> adminRoles = new ArrayList<String>();
			List<String> userRoles = new ArrayList<String>();
			
			adminRoles.add("admin");
			userRoles.add("user");
			
			// save a few users
			repository.save(new User("Jack".toUpperCase(), "pass1", adminRoles));
			repository.save(new User("Chloe".toUpperCase(), "pass2", adminRoles));
			repository.save(new User("Kim".toUpperCase(), "pass3", adminRoles));
			repository.save(new User("David".toUpperCase(), "pass4", userRoles));
			repository.save(new User("Michelle".toUpperCase(), "pass5", userRoles));

			// fetch all customers
			log.info("Users found with findAll():");
			log.info("-------------------------------");
			repository.findAll().forEach(user -> {
				log.info(user.toString());
			});
			log.info("");

//			// fetch an individual customer by ID
//			Customer customer = repository.findById(1L);
//			log.info("Customer found with findById(1L):");
//			log.info("--------------------------------");
//			log.info(customer.toString());
//			log.info("");
//
//			// fetch customers by last name
//			log.info("Customer found with findByLastName('Bauer'):");
//			log.info("--------------------------------------------");
//			repository.findByLastName("Bauer").forEach(bauer -> {
//				log.info(bauer.toString());
//			});
//			log.info("");
		};
	}

}
