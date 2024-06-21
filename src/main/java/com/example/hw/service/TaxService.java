package com.example.hw.service;

import com.example.hw.entity.ProductsEntity;
import com.example.hw.entity.StateEntity;
import com.example.hw.entity.TaxExemptionEntity;
import com.example.hw.repository.ProductsRepository;
import com.example.hw.repository.StateRepository;
import com.example.hw.repository.TaxExemptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private TaxExemptionRepository taxExemptionRepository;

    @Autowired
    private ProductsRepository productsRepository;

    public double calculateTax(String stateCode, String category, String productName, int quantity) {
        StateEntity state = stateRepository.getTaxRateByStateCode(stateCode);
        if (state == null) {
            throw new IllegalArgumentException("Invalid state code: " + stateCode);
        }

        ProductsEntity product = productsRepository.getProductByName(productName);
        if (product == null) {
            throw new IllegalArgumentException("Invalid product name: " + productName);
        }

        double price = product.getPrice();
        TaxExemptionEntity exemption = taxExemptionRepository.findByStateCodeAndCategory(stateCode, category);

        if (exemption != null && exemption.isTaxExempt()) {
            return 0;
        } else {
            return roundUp(price * quantity * state.getTaxRate());
        }
    }

    public ProductsEntity getProductByName(String productName) {
        return productsRepository.getProductByName(productName);
    }

    private double roundUp(double value) {
        return Math.ceil(value * 20.0) / 20.0;
    }
}
