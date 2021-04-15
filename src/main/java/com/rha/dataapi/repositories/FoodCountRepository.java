package com.rha.dataapi.repositories;

import com.rha.dataapi.hibernate.FoodCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCountRepository extends JpaRepository<FoodCount, Integer> {
}
