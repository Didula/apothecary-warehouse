package com.apothecary.apothecarybackend.controllers;

import com.apothecary.apothecarybackend.beans.PriceTable;
import com.apothecary.apothecarybackend.beans.Product;
import com.apothecary.apothecarybackend.repositories.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreController{

    final
    ProductRepository productRepository;

    public StoreController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> all() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{code}")
    public List<Product> product(@PathVariable String code) {
        return productRepository.findByCode(code);
    }

    @GetMapping("/products/{code}/table")
    public PriceTable table(){
        return null;
    }
}
