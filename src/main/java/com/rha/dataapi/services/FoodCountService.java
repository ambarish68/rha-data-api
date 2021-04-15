package com.rha.dataapi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.rha.dataapi.hibernate.FoodCount;
import com.rha.dataapi.repositories.FoodCountRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class FoodCountService implements ICrudService<FoodCount, Integer> {

    @Autowired
    private FoodCountRepository foodCountRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Transactional(readOnly = true)
    public List<FoodCount> getAll() {
        return foodCountRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public FoodCount get(Integer entityId) {
        return foodCountRepository.findById(entityId).orElse(null);
    }

    @Override
    public FoodCount create(FoodCount foodCountToBeCreated) {
        Preconditions.checkNotNull(foodCountToBeCreated, "Cannot create a null food count");
        Preconditions.checkNotNull(foodCountToBeCreated.getCity(), "Cannot create a food count without city");
        Preconditions.checkNotNull(foodCountToBeCreated.getFrom(), "Cannot create a food count without from date");
        Preconditions.checkNotNull(foodCountToBeCreated.getTo(), "Cannot create a food count without to date");
        try {
            log.info("creating food count with these parameters= " + objectMapper.writeValueAsString(foodCountToBeCreated));
            return foodCountRepository.save(foodCountToBeCreated);
        } catch (JsonProcessingException e) {
            log.error("error writing to json", e);
        }
        return null;
    }

    @Override
    public FoodCount update(Integer entityId, FoodCount entityToBeUpdated) {
        Preconditions.checkNotNull(entityId, "food count id cannot be null");
        Preconditions.checkNotNull(entityToBeUpdated, "Update parameters cannot be null");
        Optional<FoodCount> optionalFoodCount = foodCountRepository.findById(entityId);
        if(optionalFoodCount.isPresent()){
            FoodCount existingFoodCount=optionalFoodCount.get();
            existingFoodCount.copyAttributes(entityToBeUpdated);
            return foodCountRepository.save(existingFoodCount);
        }
        return null;
    }

    @Override
    public void delete(Integer entityId) {
        Preconditions.checkNotNull(entityId, "Food count id cannot be null");
        Optional<FoodCount> optionalCity = foodCountRepository.findById(entityId);
        if (optionalCity.isPresent()) {
            FoodCount existingFoodCount = optionalCity.get();
            existingFoodCount.setActive(false);
            foodCountRepository.save(existingFoodCount);
            return;
        }
        throw new EntityNotFoundException();
    }
}
