package com.rha.dataapi.repositories;

import com.rha.dataapi.hibernate.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AccessToken, Integer> {
}
