package com.example.accessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;

@SpringBootApplication
public class AccessingDataJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Customer("Jack", "Bauer",3.00, new Date(2345678)));
			repository.save(new Customer("Chloe", "O'Brian", 4.00, new Date(234583456)));
			repository.save(new Customer("Kim", "Bauer", 15.12, new Date(987654996)));
			repository.save(new Customer("David", "Palmer", 2.00, new Date(6198247)));
			repository.save(new Customer("Michelle", "Dessler", 2.00, new Date(14214)));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
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
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			// 	log.info(bauer.toString());
			// }
			log.info("Customer found with findByMoney('2.00'):");
			log.info("--------------------------------------------");
			repository.findByMoney(2.00).forEach(customer1 -> {
				log.info(customer1.toString());
			});
			log.info("Customer found with findByDate(new Date(14214))");
			log.info("--------------------------------------------");
			repository.findByDate(new Date(14214)).forEach(customer1 -> {
				log.info(customer1.toString());
			});


			log.info("");
		};
	}

}
