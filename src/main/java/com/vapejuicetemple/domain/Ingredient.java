package com.vapejuicetemple.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Embeddable
@Table(name = "ingredients")
public class Ingredient {
    @JsonProperty
    @Column(name = "strength")
    private int strength;
    @JsonProperty
    @Column(name = "pg")
    private int pg;
    @JsonProperty
    @Column(name = "vg")
    private int vg;
    @JsonProperty
    @Column(name = "percentage")
    private double percentage;
    @JsonProperty
    @Column(name = "ml")
    private double ml;
}
