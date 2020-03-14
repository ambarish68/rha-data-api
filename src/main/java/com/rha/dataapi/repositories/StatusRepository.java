package com.rha.dataapi.repositories;

import com.rha.dataapi.hibernate.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
}
