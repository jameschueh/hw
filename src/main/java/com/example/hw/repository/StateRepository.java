package com.example.hw.repository;

import com.example.hw.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<StateEntity, Integer> {
    StateEntity getTaxRateByStateCode(String stateCode);
}
