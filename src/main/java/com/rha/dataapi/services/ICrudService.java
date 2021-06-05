package com.rha.dataapi.services;

import com.rha.dataapi.models.SearchRequest;

import java.io.Serializable;
import java.util.List;

public interface ICrudService<Entity extends Serializable, EntityIdType> {

    List<Entity> getAll();

    Entity get(EntityIdType entityId);

    List<Entity> getWithPredicate(SearchRequest searchRequest);

    Entity create(Entity entityToBeCreated);

    Entity update(EntityIdType entityId, Entity entityToBeUpdated);

    void delete(EntityIdType entityId);
}
