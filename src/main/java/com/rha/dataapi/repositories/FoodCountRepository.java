package com.rha.dataapi.repositories;

import com.rha.dataapi.hibernate.FoodCount;
import com.rha.dataapi.hibernate.QFoodCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;

import java.time.LocalDate;
import java.util.Optional;

public interface FoodCountRepository extends JpaRepository<FoodCount, Integer>, JpaSpecificationExecutor<FoodCount>, QuerydslPredicateExecutor<FoodCount>, QuerydslBinderCustomizer<QFoodCount> {
    Optional<FoodCount> findByCity_IdEqualsAndFromEqualsAndToEquals(Integer cityId, LocalDate from, LocalDate to);
}
