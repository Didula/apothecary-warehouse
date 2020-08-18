package com.apothecary.apothecarybackend.services;

import com.apothecary.apothecarybackend.entities.Product;
import com.apothecary.apothecarybackend.modals.Price;
import com.apothecary.apothecarybackend.modals.PriceTable;
import com.apothecary.apothecarybackend.modals.PriceTableRow;
import com.apothecary.apothecarybackend.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductService {
    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Prepare the price table
     * @param productCode product code
     * @param numberOfUnits number of units
     * @return Initialized price table.
     */
    public PriceTable preparePriceTable(String productCode, int numberOfUnits) {
        PriceTable priceTable = new PriceTable();
        List<PriceTableRow> priceTableRowList = new ArrayList<>();
        Product product = this.productRepository.findByCode(productCode);
        priceTable.setCode(product.getCode());
        for (int i = 1; i <= numberOfUnits; i++) {
            PriceTableRow priceTableRow = new PriceTableRow();
            priceTableRow.setNumberOfUnits(i);
            priceTableRow.setPrice(this.calculatePrice(i,
                    product.getCartonSize(),
                    product.getCartonPrice(),
                    product.getSingleSaleMarkup(),
                    product.getCartonSaleDiscount(),
                    product.getDiscountThreshold()));
            priceTableRowList.add(priceTableRow);
        }
        priceTable.setTableRowList(priceTableRowList);
        return priceTable;
    }

    /**
     * Calculate user defined number of unit price.
     * @param productCode product code
     * @param numberOfUnits number of units
     * @return Calculated Price
     */
    public Price calculatePrice(String productCode, int numberOfUnits){
        long startTime = System.currentTimeMillis();
        Price price = new Price();
        Product product = this.productRepository.findByCode(productCode);
        price.setNumberOfUnits(numberOfUnits);
        price.setCode(productCode);
        price.setPrice(this.calculatePrice(numberOfUnits,
                product.getCartonSize(),
                product.getCartonPrice(),
                product.getSingleSaleMarkup(),
                product.getCartonSaleDiscount(),
                product.getDiscountThreshold()));
        log.info("Price calculation took " + (System.currentTimeMillis() - startTime) + " ms");
        return price;
    }

    /**
     * Price calculator
     * @param numberOfUnits number of units
     * @param cartonSize product carton size
     * @param cartonPrice product carton price
     * @param singleSaleMarkup markup to apply for a single sale
     * @param cartonSaleDiscount discount percentage as a decimal
     * @param discountThreshold after how many cartons, discount will be applied
     * @return Calculated price
     */
    public double calculatePrice(
            int numberOfUnits,
            int cartonSize,
            double cartonPrice,
            double singleSaleMarkup,
            double cartonSaleDiscount,
            double discountThreshold) {
        double singleUnitPrice = cartonPrice * singleSaleMarkup / cartonSize;
        if (numberOfUnits <= cartonSize) {
            BigDecimal bigDecimal = new BigDecimal(singleUnitPrice * numberOfUnits);
            BigDecimal bigDecimalRounded = bigDecimal.setScale(2, RoundingMode.HALF_EVEN);
            return bigDecimalRounded.doubleValue();
        } else if (numberOfUnits < (cartonSize * discountThreshold)) {
            int quotient = numberOfUnits / cartonSize;
            BigDecimal bigDecimal = new BigDecimal((numberOfUnits % cartonSize * singleUnitPrice) + quotient * cartonPrice);
            BigDecimal bigDecimalRounded = bigDecimal.setScale(2, RoundingMode.HALF_EVEN);
            return bigDecimalRounded.doubleValue();
        } else {
            int quotient = numberOfUnits / cartonSize;
            BigDecimal bigDecimal = BigDecimal.valueOf((numberOfUnits % cartonSize * singleUnitPrice) + (quotient * cartonPrice * (1 - cartonSaleDiscount)));
            BigDecimal bigDecimalRounded = bigDecimal.setScale(2, RoundingMode.HALF_EVEN);
            return bigDecimalRounded.doubleValue();
        }
    }
}
