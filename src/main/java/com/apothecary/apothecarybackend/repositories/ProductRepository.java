package com.apothecary.apothecarybackend.repositories;

import com.apothecary.apothecarybackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByCode(String code) throws HttpClientErrorException.NotFound;
}
