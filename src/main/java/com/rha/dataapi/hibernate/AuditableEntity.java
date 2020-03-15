package com.rha.dataapi.hibernate;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class AuditableEntity<T> {
    @Column(nullable = false, updatable = false, name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false, name = "modifiedAt")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date modifiedAt;

    @NotBlank
    @Column(name = "createdBy")
    private String createdBy;

    @NotBlank
    @Column(name = "modifiedBy")
    private String modifiedBy;

    @PrePersist
    @PreUpdate
    @PreRemove
    public void prePersistEntity() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (this.createdBy == null) {
            this.setCreatedBy(userDetails.getUsername());
        }
        this.setModifiedAt(new Date());
        this.setModifiedBy(userDetails.getUsername());
    }

    public abstract void copyAttributes(T t);
}
