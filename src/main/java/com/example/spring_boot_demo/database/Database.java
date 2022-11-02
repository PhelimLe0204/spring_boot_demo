package com.example.spring_boot_demo.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.spring_boot_demo.models.Product;
import com.example.spring_boot_demo.repositories.ProductRepository;

//Now connect with mysql using JPA
/*
docker run -d --rm --name mysql-spring-boot-tutorial \
-e MYSQL_ROOT_PASSWORD= \
-e MYSQL_USER=root \
-e MYSQL_PASSWORD= \
-e MYSQL_DATABASE=spring_boot_demo \
-p 3306:3306 \
--volume mysql-spring-boot-tutorial-volume:/var/lib/mysql \
mysql:latest

mysql -h localhost -P 3309 --protocol=tcp -u hoangnd -p

* */
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
                logger.info("Insert data: " + productRepository.save(productA));
                logger.info("Insert data: " + productRepository.save(productB));
            }
        };
    }
}
