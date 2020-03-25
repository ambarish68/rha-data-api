package com.rha.dataapi.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "zone")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "modifiedAt"}, allowGetters = true)
@Data
public class Zone extends IdentityEntity<Integer, Zone> implements Serializable {

    @Override
    public void copyAttributes(Zone zoneToBeCopiedFrom) {
        if (Objects.nonNull(zoneToBeCopiedFrom.getName())) {
            if (Objects.isNull(this.getName()) || !this.getName().equalsIgnoreCase(zoneToBeCopiedFrom.getName())) {
                this.setName(zoneToBeCopiedFrom.getName());
            }
        }

    }
}
