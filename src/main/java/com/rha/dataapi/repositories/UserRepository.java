package com.rha.dataapi.repositories;

import com.rha.dataapi.hibernate.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmailId(String emailId);
}
