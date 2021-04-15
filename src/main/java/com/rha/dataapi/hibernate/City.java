package com.rha.dataapi.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "city")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "modifiedAt"}, allowGetters = true)
@Getter
@Setter
public class City extends IdentityEntity<Integer, City> implements Serializable {

    @NotBlank
    @Column(name = "emailId")
    private String emailId;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "zoneId", referencedColumnName = "id")
    private Zone zone;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "statusId", referencedColumnName = "id")
    private Status status;

    @Column(name = "facebookGroupLink")
    private String facebookGroupLink;

    @Column(name = "whatsappGroupLink")
    private String whatsappGroupLink;

    @Column(name = "whatsappCadetsGroupLink")
    private String whatsappCadetsGroupLink;

    @Column(nullable = false, name = "launchedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date launchedAt;

//    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "city")
//    private List<CityRobinRelation> pointsOfContact;

    /**
     * Only allow city email, FB group link, WhatsApp group link, WhatsApp cadets group link, zone and statuses to be updated.
     */
    @Override
    public void copyAttributes(City cityToBeCopiedFrom) {
        if (Objects.nonNull(cityToBeCopiedFrom.getEmailId())) {
            if (Objects.isNull(this.getEmailId()) || !this.getEmailId().equalsIgnoreCase(cityToBeCopiedFrom.getEmailId())) {
                this.setEmailId(cityToBeCopiedFrom.getEmailId());
            }
        }
        if (Objects.nonNull(cityToBeCopiedFrom.getFacebookGroupLink())) {
            if (Objects.isNull(this.getFacebookGroupLink()) || !this.getFacebookGroupLink().equalsIgnoreCase(cityToBeCopiedFrom.getFacebookGroupLink())) {
                this.setFacebookGroupLink(cityToBeCopiedFrom.getFacebookGroupLink());
            }
        }
        if (Objects.nonNull(cityToBeCopiedFrom.getWhatsappGroupLink())) {
            if (Objects.isNull(this.getWhatsappGroupLink()) || !this.getWhatsappGroupLink().equalsIgnoreCase(cityToBeCopiedFrom.getWhatsappGroupLink())) {
                this.setWhatsappGroupLink(cityToBeCopiedFrom.getWhatsappGroupLink());
            }
        }
        if (Objects.nonNull(cityToBeCopiedFrom.getWhatsappCadetsGroupLink())) {
            if (Objects.isNull(this.getWhatsappCadetsGroupLink()) || !this.getWhatsappCadetsGroupLink().equalsIgnoreCase(cityToBeCopiedFrom.getWhatsappCadetsGroupLink())) {
                this.setWhatsappCadetsGroupLink(cityToBeCopiedFrom.getWhatsappCadetsGroupLink());
            }
        }
        if (Objects.nonNull(cityToBeCopiedFrom.getZone())) {
            if (Objects.isNull(this.getZone()) || !this.getZone().equals(cityToBeCopiedFrom.getZone())) {
                this.setZone(cityToBeCopiedFrom.getZone());
            }
        }
        if (Objects.nonNull(cityToBeCopiedFrom.getStatus())) {
            if (Objects.isNull(this.getStatus()) || !this.getStatus().equals(cityToBeCopiedFrom.getStatus())) {
                this.setStatus(cityToBeCopiedFrom.getStatus());
            }
        }
    }
}
