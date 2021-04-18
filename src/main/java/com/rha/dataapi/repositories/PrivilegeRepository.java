package com.rha.dataapi.repositories;

import com.rha.dataapi.hibernate.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer>, JpaSpecificationExecutor<Privilege> {
}
