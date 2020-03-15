package com.rha.dataapi.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "zone")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "modifiedAt"}, allowGetters = true)
@Data
public class Zone extends AuditableEntity<Zone> implements Serializable {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Boolean active;

    @Override
    public void copyAttributes(Zone zoneToBeCopiedFrom) {
        if (Objects.nonNull(zoneToBeCopiedFrom.getName())) {
            if (Objects.isNull(this.getName()) || !this.getName().equalsIgnoreCase(zoneToBeCopiedFrom.getName())) {
                this.setName(zoneToBeCopiedFrom.getName());
            }
        }

    }
}
