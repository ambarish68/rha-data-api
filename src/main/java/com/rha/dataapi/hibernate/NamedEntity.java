package com.rha.dataapi.hibernate;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class NamedEntity<EntityIdType extends Serializable, Entity> extends IdentityEntity<EntityIdType, Entity> {
    @NotBlank
    @Column(name = "name")
    private String name;
}
