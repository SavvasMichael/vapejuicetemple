package com.vapejuicetemple.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "recipe")
@NamedQueries({@NamedQuery(name = "findsAll", query = "select e from JuiceRecipe e")})
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JuiceRecipe {
    @JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
