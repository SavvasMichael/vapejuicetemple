package com.vapejuicetemple.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Embeddable
@EqualsAndHashCode
@ToString
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
