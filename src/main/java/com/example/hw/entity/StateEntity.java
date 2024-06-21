package com.example.hw.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "states")
public class StateEntity {

    @Id
    @Column(name = "state_code", nullable = false, length = 2)
    private String stateCode;

    @Column(nullable = false)
    private String name;

    @Column(name = "tax_rate", nullable = false)
    private double taxRate;

    // Getters and Setters
    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
}
