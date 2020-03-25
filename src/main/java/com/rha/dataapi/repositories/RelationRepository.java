package com.rha.dataapi.repositories;

import com.rha.dataapi.hibernate.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends JpaRepository<Relation, Integer> {
}
