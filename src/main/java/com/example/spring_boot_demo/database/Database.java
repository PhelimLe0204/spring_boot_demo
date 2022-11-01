package com.example.spring_boot_demo.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.spring_boot_demo.models.Product;
import com.example.spring_boot_demo.repositories.ProductRepository;

@Configuration
public class Database {
    // Logger
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product productA = new Product("Macbook pro 16 inch", 2020, 2400.0, "");
                Product productB = new Product("Ipad air green", 2021, 599.0, "");
                Product productC = new Product("Laptop DELL core i5", 2022, 699.0, "");
                logger.info("Insert data: " + productRepository.save(productA));
                logger.info("Insert data: " + productRepository.save(productB));
                logger.info("Insert data: " + productRepository.save(productC));
            }
        };
    }
}
