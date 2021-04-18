package com.rha.dataapi.repositories;

import com.rha.dataapi.hibernate.Robin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RobinRepository extends JpaRepository<Robin, Integer>, JpaSpecificationExecutor<Robin> {
}
