package com.rha.dataapi.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "relation")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "modifiedAt"}, allowGetters = true)
@Getter
@Setter
public class Relation extends IdentityEntity<Integer, Relation> implements Serializable {

    @Column(name = "description")
    private String description;

    @Override
    public void copyAttributes(Relation relationToBeCopiedFrom) {
        if (StringUtils.isNotEmpty(relationToBeCopiedFrom.getName())) {
            if (!this.getName().equals(relationToBeCopiedFrom.getName())) {
                this.setName(relationToBeCopiedFrom.getName());
            }
        }
        if (StringUtils.isNotEmpty(relationToBeCopiedFrom.getDescription())) {
            if (!this.getDescription().equals(relationToBeCopiedFrom.getDescription())) {
                this.setDescription(relationToBeCopiedFrom.getDescription());
            }
        }
    }
}
