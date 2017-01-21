package com.vapejuicetemple.resource;

import JuiceRecipeBuilder.JuiceRecipeBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vapejuicetemple.dao.JuiceRecipeDAO;
import com.vapejuicetemple.domain.JuiceRecipe;
import com.vapejuicetemple.service.JuiceService;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class JuiceResourceTest {

    @Mock
    private static JuiceRecipeDAO juiceRecipeDAO;
    @InjectMocks
    private static JuiceService juiceService = new JuiceService(juiceRecipeDAO);

    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final String BASE_URL = "/juicerecipe";

    private JuiceRecipeBuilder juiceRecipeBuilder = new JuiceRecipeBuilder();

    private JuiceRecipe juiceRecipe = juiceRecipeBuilder.aRandomJuiceRecipe();
    private JuiceRecipe anotherJuiceRecipe = juiceRecipeBuilder.aRandomJuiceRecipe();

    @ClassRule
    public static final ResourceTestRule resourceTestRule = ResourceTestRule.builder()
            .addResource(new JuiceResource(juiceService)).build();

    @Test
    public void saveJuiceRecipeReturnsSavedRecipe() {
        //when
        given(juiceRecipeDAO.saveJuiceRecipe(juiceRecipe)).willReturn(juiceRecipe);
        Response savedJuiceRecipe = resourceTestRule.client().target(BASE_URL).request()
                .post(Entity.entity(juiceRecipe, MediaType.APPLICATION_JSON));

        //then
        assertEquals(savedJuiceRecipe.getStatus(), 200);
    }

    @Test
    public void getJuiceRecipeReturnsCorrectRecipe() {
        //when
        given(juiceRecipeDAO.getJuiceRecipeById(1)).willReturn(Optional.of(juiceRecipe));
        Response fetchedJuiceRecipe = resourceTestRule.client().target(BASE_URL + "/1").request().get();

        //then
        assertThat(fetchedJuiceRecipe.readEntity(JuiceRecipe.class)).isEqualTo(juiceRecipe);
    }

    @Test
    public void getJuiceRecipesReturnsAllRecipes() throws IOException {
        //when
        given(juiceRecipeDAO.getAllJuiceRecipes()).willReturn(asList(juiceRecipe, anotherJuiceRecipe));
        Response fetchedJuiceRecipes = resourceTestRule.client().target(BASE_URL).request().get();
        String jsonRecipeList = OBJECT_MAPPER.writeValueAsString(asList(juiceRecipe, anotherJuiceRecipe));

        //then
        assertThat(jsonRecipeList).isEqualTo(fetchedJuiceRecipes.readEntity(String.class));
    }

    @Test
    public void deleteJuiceRecipeDeletesRecipe() {
        //when
        given(juiceRecipeDAO.deleteJuiceRecipe(1)).willReturn(juiceRecipe);
        Response deletedJuiceRecipe = resourceTestRule.client().target(BASE_URL + "/1").request().delete();

        //then
        assertThat(deletedJuiceRecipe.readEntity(JuiceRecipe.class)).isEqualTo(juiceRecipe);
    }
}