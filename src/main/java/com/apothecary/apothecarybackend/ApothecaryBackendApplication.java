package com.apothecary.apothecarybackend;

import com.apothecary.apothecarybackend.beans.Product;
import com.apothecary.apothecarybackend.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApothecaryBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApothecaryBackendApplication.class, args);
    }

    // Using H2 in memory db for product data.
    // Initializing db at the beginning.
    @Bean
    CommandLineRunner runner(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product("PEN_EARS", "Penguin Ears", 20, 175, 1.3, 0.1, 3));
            productRepository.save(new Product("HORSE_SHOE", "Horse Shoe", 5, 825, 1.3, 0.1, 3));
        };
    }
}
