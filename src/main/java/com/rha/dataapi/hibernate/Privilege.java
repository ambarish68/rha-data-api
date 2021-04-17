package com.rha.dataapi.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "privilege")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "modifiedAt"}, allowGetters = true)
@Data
public class Privilege extends NamedEntity<Integer, Privilege> implements Serializable {

    @Override
    public void copyAttributes(Privilege privilegeToBeCopiedFrom) {
        if (Objects.nonNull(privilegeToBeCopiedFrom.getName())) {
            if (Objects.isNull(this.getName()) || !this.getName().equals(privilegeToBeCopiedFrom.getName())) {
                this.setName(privilegeToBeCopiedFrom.getName());
            }
        }
    }
}
