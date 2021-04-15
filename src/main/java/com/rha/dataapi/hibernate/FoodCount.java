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
@Table(name = "foodCount")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "modifiedAt"}, allowGetters = true)
@Getter
@Setter
public class FoodCount extends IdentityEntity<Integer, FoodCount> implements Serializable {

    @NotBlank
    @Column(name = "count")
    private int count;

    @Column(nullable = false, name = "from")
    @Temporal(TemporalType.TIMESTAMP)
    private Date from;

    @Column(nullable = false, name = "to")
    @Temporal(TemporalType.TIMESTAMP)
    private Date to;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "cityId", referencedColumnName = "id")
    private City city;


    @Override
    public void copyAttributes(FoodCount fromFoodCount) {
        if (Objects.nonNull(fromFoodCount)) {
            if (Objects.nonNull(fromFoodCount.count) && fromFoodCount.getCount() != this.count) {
                this.setCount(fromFoodCount.getCount());
            }
            if (Objects.nonNull(fromFoodCount.getFrom()) && fromFoodCount.getFrom() != this.from) {
                this.setFrom(fromFoodCount.getFrom());
            }
            if (Objects.nonNull(fromFoodCount.getTo()) && fromFoodCount.getTo() != this.from) {
                this.setTo(fromFoodCount.getTo());
            }
        }
    }
}
