package com.rha.dataapi.hibernate;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class IdentityEntity<EntityIdType extends Serializable, Entity> extends AuditableEntity<Entity> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private EntityIdType id;

    @Column(name = "active")
    private Boolean active;
}
