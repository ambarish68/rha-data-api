package com.rha.dataapi.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;


@Entity
@Table(name = "role")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "modifiedAt"}, allowGetters = true)
@Data
public class Role extends IdentityEntity<Integer, Role> implements Serializable {

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
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
