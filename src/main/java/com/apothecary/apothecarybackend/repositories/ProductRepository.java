package com.apothecary.apothecarybackend.repositories;

import com.apothecary.apothecarybackend.beans.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCode(String code);
}
