package com.example.hw.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tax_exemptions")
public class TaxExemptionEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "state_code", nullable = false, length = 2)
        private String stateCode;

        @Column(nullable = false)
        private String category;

        @Column(name = "is_tax_exempt", nullable = false)
        private boolean isTaxExempt;

        // Getters and Setters
        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getStateCode() {
                return stateCode;
        }

        public void setStateCode(String stateCode) {
                this.stateCode = stateCode;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        public boolean isTaxExempt() {
                return isTaxExempt;
        }

        public void setTaxExempt(boolean isTaxExempt) {
                this.isTaxExempt = isTaxExempt;
        }
}
