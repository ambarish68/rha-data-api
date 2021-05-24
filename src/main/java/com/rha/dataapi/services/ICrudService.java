package com.rha.dataapi.services;

import com.rha.dataapi.filters.SearchCriteria;

import java.io.Serializable;
import java.util.List;

public interface ICrudService<Entity extends Serializable, EntityIdType> {

    List<Entity> getAll();

    Entity get(EntityIdType entityId);

    List<Entity> getWithPredicate(List<SearchCriteria> searchCriteriaList);

    Entity create(Entity entityToBeCreated);

    Entity update(EntityIdType entityId, Entity entityToBeUpdated);

    void delete(EntityIdType entityId);
}
