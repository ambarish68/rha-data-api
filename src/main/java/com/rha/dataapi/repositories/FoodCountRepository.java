package com.rha.dataapi.repositories;

import com.rha.dataapi.hibernate.FoodCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.Optional;

public interface FoodCountRepository extends JpaRepository<FoodCount, Integer>, JpaSpecificationExecutor<FoodCount> {
    Optional<FoodCount> findByCity_IdEqualsAndFromEqualsAndToEquals(Integer cityId, LocalDate from, LocalDate to);
}
