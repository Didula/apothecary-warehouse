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
public class PriceTableRow implements Serializable {
    private int numberOfUnits;
    private double price;
}
