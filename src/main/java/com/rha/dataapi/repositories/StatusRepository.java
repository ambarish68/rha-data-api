package com.rha.dataapi.repositories;

import com.rha.dataapi.hibernate.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    Optional<Status> findStatusByName(String name);
}
