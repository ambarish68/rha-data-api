package com.rha.dataapi.repositories;

import com.rha.dataapi.hibernate.FoodCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface FoodCountRepository extends JpaRepository<FoodCount, Integer> {
    Optional<FoodCount> findByCity_IdEqualsAndFromEqualsAndToEquals(Integer cityId, LocalDate from, LocalDate to);
}
