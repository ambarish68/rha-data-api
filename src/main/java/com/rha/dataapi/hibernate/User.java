package com.rha.dataapi.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "modifiedAt"}, allowGetters = true)
@Data
public class User extends AuditableEntity<User> implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "emailId")
    private String emailId;

    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;

    @Override
    public void copyAttributes(User userToBeCopiedFrom) {
        if (Objects.nonNull(userToBeCopiedFrom.getName())) {
            if (Objects.isNull(this.getName()) || !this.getName().equalsIgnoreCase(userToBeCopiedFrom.getName())) {
                this.setName(userToBeCopiedFrom.getName());
            }
        }
        if (Objects.nonNull(userToBeCopiedFrom.getPassword())) {
            if (Objects.isNull(this.getPassword()) || !this.getPassword().equals(userToBeCopiedFrom.getPassword())) {
                this.setPassword(userToBeCopiedFrom.getPassword());
            }
        }
        if (Objects.nonNull(userToBeCopiedFrom.getActive())) {
            if (Objects.isNull(this.getActive()) || !this.getActive().equals(userToBeCopiedFrom.getActive())) {
                this.setActive(userToBeCopiedFrom.getActive());
            }
        }
    }
}
