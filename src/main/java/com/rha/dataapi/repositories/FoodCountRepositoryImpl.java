package com.rha.dataapi.repositories;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

@NoRepositoryBean
public class FoodCountRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements FoodCountRepositoryCustom<T, ID>,Serializable{

    public FoodCountRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }
}