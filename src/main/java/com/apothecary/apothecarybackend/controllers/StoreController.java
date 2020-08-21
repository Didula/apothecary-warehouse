package com.apothecary.apothecarybackend.controllers;

import com.apothecary.apothecarybackend.entities.Product;
import com.apothecary.apothecarybackend.exception.StoreControllerNotFoundException;
import com.apothecary.apothecarybackend.exception.StoreControllerValidationException;
import com.apothecary.apothecarybackend.modals.Price;
import com.apothecary.apothecarybackend.modals.PriceTable;
import com.apothecary.apothecarybackend.repositories.ProductRepository;
import com.apothecary.apothecarybackend.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Product>> all() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/products/{code}")
    public ResponseEntity<Product> product(@PathVariable String code) throws StoreControllerNotFoundException {
        log.info("Fetch product request received for " + code);
        Product product;
        try {
            product = productRepository.findByCode(code);
        } catch (Exception e) {
            throw new StoreControllerNotFoundException();
        }
        if (product == null) {
            throw new StoreControllerNotFoundException();
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products/{code}/table")
    public ResponseEntity<PriceTable> table(@PathVariable String code, @RequestParam int units) throws StoreControllerValidationException {
        log.info("price table preparation request received for " + code);
        if(units < 1){
            throw new StoreControllerValidationException();
        }
        return new ResponseEntity<>(productService.preparePriceTable(code, units), HttpStatus.OK);
    }

    @GetMapping("/products/{code}/price")
    public ResponseEntity<Price> price(@PathVariable String code, @RequestParam int units) throws StoreControllerValidationException {
        log.info("price calculation request received");
        if(units < 1){
            throw new StoreControllerValidationException();
        }
        return new ResponseEntity<>(productService.calculatePrice(code, units), HttpStatus.OK);
    }
}
