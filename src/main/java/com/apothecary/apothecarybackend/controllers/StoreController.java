package com.apothecary.apothecarybackend.controllers;

import com.apothecary.apothecarybackend.entities.Product;
import com.apothecary.apothecarybackend.modals.Price;
import com.apothecary.apothecarybackend.modals.PriceTable;
import com.apothecary.apothecarybackend.repositories.ProductRepository;
import com.apothecary.apothecarybackend.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class StoreController {

    final ProductRepository productRepository;
    final ProductService productService;

    public StoreController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> all() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{code}")
    public Product product(@PathVariable String code) {
        return productRepository.findByCode(code);
    }

    @GetMapping("/products/{code}/table")
    public PriceTable table(@PathVariable String code, @RequestParam int units) {
        return productService.preparePriceTable(code, units);
    }

    @GetMapping("/products/{code}/price")
    public Price price(@PathVariable String code, @RequestParam int units) {
        log.info("price calculation request received");
        return productService.calculatePrice(code, units);
    }
}
