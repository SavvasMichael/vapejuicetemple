package com.vapejuicetemple.service;

import com.vapejuicetemple.domain.Ingredient;
import com.vapejuicetemple.domain.JuiceRecipe;
import com.vapejuicetemple.exception.InvalidJuiceRecipeException;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class JuiceServiceTest {
    private JuiceRecipe juiceRecipe = JuiceRecipe.builder()
            .name("AwesomeJuice")
            .description("AwesomeDescription")
            .ingredients(singletonList(Ingredient.builder()
                    .pg(30)
                    .vg(70)
                    .strength(3)
                    .percentage(0.5)
                    .build()))
            .build();

    private JuiceRecipe anotherJuiceRecipe = JuiceRecipe.builder()
            .name("AwesomeJuice")
            .description("AwesomeDescription")
            .ingredients(singletonList(Ingredient.builder()
                    .pg(30)
                    .vg(70)
                    .strength(3)
                    .percentage(0.5)
                    .build()))
            .build();

    private JuiceService juiceService = new JuiceService();

    @Test
    public void addsRecipeToList() {
        //when
        List<JuiceRecipe> juiceRecipes = juiceService.insertJuiceRecipe(juiceRecipe);

        //then
        assertThat(juiceRecipes.get(0)).isSameAs(juiceRecipe);
    }

    @Test
    public void returnsException_givenJuiceRecipe_isNull() {
        //when
        Throwable exception = catchThrowable(() -> juiceService.insertJuiceRecipe(null));

        //then
        assertThat(exception)
                .isInstanceOf(InvalidJuiceRecipeException.class)
                .hasMessage("Juice recipe was null");
    }

    @Test
    public void returnsAllJuiceRecipes() {
        //given
        juiceService.insertJuiceRecipe(juiceRecipe);
        juiceService.insertJuiceRecipe(anotherJuiceRecipe);

        //when
        List<JuiceRecipe> juiceRecipes = juiceService.getJuiceRecipes();

        //then
        assertThat(juiceRecipes).hasSize(2);
        assertThat(juiceRecipes.containsAll(asList(juiceRecipe, anotherJuiceRecipe)));
    }
}