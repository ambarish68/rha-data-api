package com.rha.dataapi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.rha.dataapi.hibernate.Relation;
import com.rha.dataapi.repositories.RelationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
public class RelationService implements ICrudService<Relation, Integer> {

    @Autowired
    private RelationRepository relationRepository;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    @Transactional(readOnly = true)
    public List<Relation> getAll() {
        return relationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Relation get(Integer entityId) {
        Optional<Relation> optionalRelation = relationRepository.findById(entityId);
        if (optionalRelation.isPresent()) {
            return optionalRelation.get();
        }
        throw new EntityNotFoundException();
    }

    @Override
    public Relation create(Relation entityToBeCreated) {
        Preconditions.checkNotNull(entityToBeCreated, "Cannot create a null relation");
        Preconditions.checkNotNull(entityToBeCreated.getName(), "Cannot create a relation without a name");
        try {
            log.info("creating city with the parameters: " + objectMapper.writeValueAsString(entityToBeCreated));
            if(Objects.isNull(entityToBeCreated.getActive())){
                entityToBeCreated.setActive(true);
            }
            entityToBeCreated = relationRepository.save(entityToBeCreated);
            return entityToBeCreated;
        } catch (Exception e) {
            log.info("Error creating relation ", e);
        }
        return null;
    }

    @Override
    public Relation update(Integer entityId, Relation entityToBeUpdated) {
        Preconditions.checkNotNull(entityId, "Relation id cannot be null");
        Preconditions.checkNotNull(entityToBeUpdated, "Update parameters cannot be null");
        Optional<Relation> optionalRelation = relationRepository.findById(entityId);
        if (optionalRelation.isPresent()) {
            Relation existingRelation = optionalRelation.get();
            existingRelation.copyAttributes(entityToBeUpdated);
            return relationRepository.save(entityToBeUpdated);
        }
        throw new EntityNotFoundException();
    }

    @Override
    public void delete(Integer entityId) {
        Preconditions.checkNotNull(entityId, "Relation id cannot be null");
        Optional<Relation> optionalRelation = relationRepository.findById(entityId);
        if (optionalRelation.isPresent()) {
            Relation existingRelation = optionalRelation.get();
            existingRelation.setActive(false);
            relationRepository.save(existingRelation);
            return;
        }
        throw new EntityNotFoundException("Existing relation not found with id " + entityId);
    }
}
