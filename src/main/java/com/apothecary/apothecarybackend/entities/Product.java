package com.apothecary.apothecarybackend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String code;
    private String name;
    private int cartonSize;
    private double cartonPrice;
    private double singleSaleMarkup;
    private double cartonSaleDiscount;
    private double discountThreshold;

    public Product(
            String code,
            String name,
            int cartonSize,
            double cartonPrice,
            double singleSaleMarkup,
            double cartonSaleDiscount,
            double discountThreshold) {
        this.code = code;
        this.name = name;
        this.cartonSize = cartonSize;
        this.cartonPrice = cartonPrice;
        this.singleSaleMarkup = singleSaleMarkup;
        this.cartonSaleDiscount = cartonSaleDiscount;
        this.discountThreshold = discountThreshold;
    }
}
