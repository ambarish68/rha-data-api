package com.rha.dataapi.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cityRelation")
@Data
public class CityRobinRelation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "cityId", referencedColumnName = "id")
    @JsonIgnoreProperties("pointsOfContact")
    private City city;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "robinId", referencedColumnName = "id")
    private Robin robin;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "relationId", referencedColumnName = "id")
    private Relation relation;
}
