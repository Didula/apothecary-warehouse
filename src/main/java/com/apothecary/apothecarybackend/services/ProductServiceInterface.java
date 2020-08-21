package com.apothecary.apothecarybackend.services;

import com.apothecary.apothecarybackend.modals.Price;
import com.apothecary.apothecarybackend.modals.PriceTable;

public interface ProductServiceInterface {
    PriceTable preparePriceTable(String productCode, int numberOfUnits);

    Price calculatePrice(String productCode, int numberOfUnits);

    double calculatePrice(
            int numberOfUnits,
            int cartonSize,
            double cartonPrice,
            double singleSaleMarkup,
            double cartonSaleDiscount,
            double discountThreshold);
}
