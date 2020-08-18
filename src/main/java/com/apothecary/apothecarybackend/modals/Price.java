package com.apothecary.apothecarybackend.modals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Price implements Serializable {
    private String code;
    private double price;
    private int numberOfUnits;
}
