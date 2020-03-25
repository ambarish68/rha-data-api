package com.rha.dataapi.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "robin")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "modifiedAt"}, allowGetters = true)
@Getter
@Setter
public class Robin extends IdentityEntity<Integer, Robin> implements Serializable {


    @Column(name = "emailId")
    private String emailId;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "bloodGroup")
    private String bloodGroup;

    @Column(name = "temporaryAddress")
    private String temporaryAddress;

    @Column(name = "permanentAddress")
    private String permanentAddress;

    @Column(name = "emergencyContactNumber")
    private String emergencyContactNumber;

//    @ManyToMany(mappedBy = "robins")
//    @JsonIgnoreProperties({"robins","pointsOfContact"})
//    private List<City> cities;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "robin")
    private List<CityRobinRelation> relatedToCities;

    @Override
    public void copyAttributes(Robin robinToBeCopiedFrom) {
        if (Objects.nonNull(robinToBeCopiedFrom.getName())) {
            if (Objects.isNull(this.getName()) || !this.getName().equals(robinToBeCopiedFrom.getName())) {
                this.setName(robinToBeCopiedFrom.getName());
            }
        }
        if (Objects.nonNull(robinToBeCopiedFrom.getBloodGroup())) {
            if (Objects.isNull(this.getBloodGroup()) || !this.getBloodGroup().equals(robinToBeCopiedFrom.getBloodGroup())) {
                this.setBloodGroup(robinToBeCopiedFrom.getBloodGroup());
            }
        }
        if (Objects.nonNull(robinToBeCopiedFrom.getEmailId())) {
            if (Objects.isNull(this.getEmailId()) || !this.getEmailId().equals(robinToBeCopiedFrom.getEmailId())) {
                this.setEmailId(robinToBeCopiedFrom.getEmailId());
            }
        }
        if (Objects.nonNull(robinToBeCopiedFrom.getTemporaryAddress())) {
            if (Objects.isNull(this.getTemporaryAddress()) || !this.getTemporaryAddress().equals(robinToBeCopiedFrom.getTemporaryAddress())) {
                this.setTemporaryAddress(robinToBeCopiedFrom.getTemporaryAddress());
            }
        }
        if (Objects.nonNull(robinToBeCopiedFrom.getPermanentAddress())) {
            if (Objects.isNull(this.getPermanentAddress()) || !this.getPermanentAddress().equals(robinToBeCopiedFrom.getPermanentAddress())) {
                this.setPermanentAddress(robinToBeCopiedFrom.getPermanentAddress());
            }
        }
        if (Objects.nonNull(robinToBeCopiedFrom.getPhoneNumber())) {
            if (Objects.isNull(this.getPhoneNumber()) || !this.getPhoneNumber().equals(robinToBeCopiedFrom.getPhoneNumber())) {
                this.setPhoneNumber(robinToBeCopiedFrom.getPhoneNumber());
            }
        }
        if (Objects.nonNull(robinToBeCopiedFrom.getEmergencyContactNumber())) {
            if (Objects.isNull(this.getEmergencyContactNumber()) || !this.getEmergencyContactNumber().equals(robinToBeCopiedFrom.getEmergencyContactNumber())) {
                this.setEmergencyContactNumber(robinToBeCopiedFrom.getEmergencyContactNumber());
            }
        }
    }
}
