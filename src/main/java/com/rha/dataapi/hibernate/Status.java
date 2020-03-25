package com.rha.dataapi.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "status")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "modifiedAt"}, allowGetters = true)
@Data
public class Status extends IdentityEntity<Integer, Status> implements Serializable {

    @Override
    public void copyAttributes(Status statusToBeUpdated) {
        //Do nothing, as there are not exactly any attributes to copy
    }
}
