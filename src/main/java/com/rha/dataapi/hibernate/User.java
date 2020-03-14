package com.rha.dataapi.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "modifiedAt"}, allowGetters = true)
@Data
public class User extends AuditableEntity {
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
}
