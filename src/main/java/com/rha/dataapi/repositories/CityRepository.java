package com.rha.dataapi.repositories;

import com.rha.dataapi.hibernate.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>, JpaSpecificationExecutor<City> {
    Optional<City> findByName(String name);
}
