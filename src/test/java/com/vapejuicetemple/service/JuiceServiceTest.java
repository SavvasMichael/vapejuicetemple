//package com.vapejuicetemple.service;
//
//import com.vapejuicetemple.dao.JuiceRecipeDAO;
//import com.vapejuicetemple.domain.Ingredient;
//import com.vapejuicetemple.domain.JuiceRecipe;
//import com.vapejuicetemple.exception.InvalidJuiceRecipeException;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import java.util.List;
//
//import static java.util.Arrays.asList;
//import static java.util.Collections.singletonList;
//import static org.assertj.core.api.Assertions.catchThrowable;
//import static org.assertj.core.api.Java6Assertions.assertThat;
//
//@RunWith(MockitoJUnitRunner.class)
//public class JuiceServiceTest {
//
//    @Mock
//    private JuiceRecipeDAO juiceRecipeDAO;
//
//    private JuiceRecipe juiceRecipe = JuiceRecipe.builder()
//            .name("AwesomeJuice")
//            .description("AwesomeDescription")
//            .ingredients(singletonList(Ingredient.builder()
//                    .pg(30)
//                    .vg(70)
//                    .strength(3)
//                    .percentage(0.5)
//                    .build()))
//            .build();
//
//    private JuiceRecipe anotherJuiceRecipe = JuiceRecipe.builder()
//            .name("AwesomeJuice")
//            .description("AwesomeDescription")
//            .ingredients(singletonList(Ingredient.builder()
//                    .pg(30)
//                    .vg(70)
//                    .strength(3)
//                    .percentage(0.5)
//                    .build()))
//            .build();
//
//    private JuiceService juiceService = new JuiceService(juiceRecipeDAO);
//
//    @Test @Ignore
//    public void addsRecipeToList() {
//        //when
//        JuiceRecipe savedRecipe = juiceService.insertJuiceRecipe(juiceRecipe);
//
//        //then
//        assertThat(savedRecipe).isSameAs(savedRecipe);
//    }
//
//    @Test
//    public void returnsException_givenJuiceRecipe_isNull() {
//        //when
//        Throwable exception = catchThrowable(() -> juiceService.insertJuiceRecipe(null));
//
//        //then
//        assertThat(exception)
//                .isInstanceOf(InvalidJuiceRecipeException.class)
//                .hasMessage("Juice recipe was null");
//    }
//
//    @Test @Ignore
//    public void returnsAllJuiceRecipes() {
//        //given
//        juiceService.insertJuiceRecipe(juiceRecipe);
//        juiceService.insertJuiceRecipe(anotherJuiceRecipe);
//
//        //when
//        List<JuiceRecipe> juiceRecipes = juiceService.getJuiceRecipes();
//
//        //then
//        assertThat(juiceRecipes).hasSize(2);
//        assertThat(juiceRecipes.containsAll(asList(juiceRecipe, anotherJuiceRecipe)));
//    }
//}