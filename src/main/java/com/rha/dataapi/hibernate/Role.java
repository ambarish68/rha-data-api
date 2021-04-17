package com.rha.dataapi.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "role")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "modifiedAt"}, allowGetters = true)
@Data
public class Role extends NamedEntity<Integer, Role> implements Serializable {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rolePrivilege",
            joinColumns = @JoinColumn(
                    name = "roleId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilegeId", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    @Override
    public void copyAttributes(Role roleToBeCopiedFrom) {
        if (Objects.nonNull(roleToBeCopiedFrom.getName())) {
            if (Objects.isNull(this.getName()) || !this.getName().equals(roleToBeCopiedFrom.getName())) {
                this.setName(roleToBeCopiedFrom.getName());
            }
        }
    }
}
