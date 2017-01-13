package com.vapejuicetemple.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(appliesTo = "juice_recipes")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JuiceRecipe {
    @JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @JsonProperty
    @Column(name = "name")
    private String name;
    @JsonProperty
    @Column(name = "description")
    private String description;
    @Wither
    @JsonProperty
    @Embedded
    @Column(name = "Ingredients")
    private List<Ingredient> ingredients;
}
