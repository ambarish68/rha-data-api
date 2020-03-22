package com.rha.dataapi.hibernate;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@MappedSuperclass
public abstract class IdentityEntity<EntityIdType, Entity> extends AuditableEntity<Entity> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private EntityIdType id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Boolean active;
}
