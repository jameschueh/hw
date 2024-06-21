package com.example.hw.repository;

import com.example.hw.entity.TaxExemptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxExemptionRepository extends JpaRepository<TaxExemptionEntity, Integer> {
    TaxExemptionEntity findByStateCodeAndCategory(String stateCode, String category);
}
