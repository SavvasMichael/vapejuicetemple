package JuiceRecipeBuilder;

import com.vapejuicetemple.domain.Ingredient;
import com.vapejuicetemple.domain.JuiceRecipe;

import java.util.Random;

import static java.util.Collections.singletonList;

public class JuiceRecipeBuilder {
    private final Random RANDOM = new Random();

    public JuiceRecipe aRandomJuiceRecipe() {
        return JuiceRecipe.builder()
                .name("someRecipe")
                .description("someRecipeDescription")
                .ingredients(singletonList(Ingredient.builder()
                        .ml(RANDOM.nextInt(30))
                        .strength(RANDOM.nextInt(18))
                        .vg(RANDOM.nextInt(100))
                        .pg(RANDOM.nextInt(100))
                        .percentage(RANDOM.nextInt(100))
                        .build()))
                .build();
    }
}
