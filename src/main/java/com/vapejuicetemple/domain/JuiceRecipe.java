package com.vapejuicetemple.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Wither;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "juice_recipes")
@SecondaryTable(name="juice_recipes_ingredients", pkJoinColumns=@PrimaryKeyJoinColumn(name="JuiceRecipe_Id"))
@NamedQueries({@NamedQuery(name = "findsAll", query = "select jr from JuiceRecipe jr"),
               @NamedQuery(name = "deleteById", query = "delete from JuiceRecipe jr where jr.id = :id")})
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class JuiceRecipe {
    @JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;
    @Wither
    @JsonProperty
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="strength", column=@Column(name="strength", table="strength")),
            @AttributeOverride(name="pg", column=@Column(name="pg", table="pg")),
            @AttributeOverride(name="vg", column=@Column(name="vg", table="vg")),
            @AttributeOverride(name="percentage", column=@Column(name="percentage", table="percentage")),
            @AttributeOverride(name="ml", column=@Column(name="ml", table="ml"))
    })
    private List<Ingredient> ingredients;
}
