package com.rha.dataapi.services;

import java.util.List;

public interface ICrudService<Entity, EntityIdType> {
    List<Entity> getAll();

    Entity get(EntityIdType entityId);

    Entity create(Entity entityToBeCreated);

    Entity update(EntityIdType entityId, Entity entityToBeUpdated);

    void delete(EntityIdType entityId);
}
