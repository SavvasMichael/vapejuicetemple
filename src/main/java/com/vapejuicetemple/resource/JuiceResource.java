package com.vapejuicetemple.resource;

import com.codahale.metrics.annotation.Timed;
import com.vapejuicetemple.domain.JuiceRecipe;
import com.vapejuicetemple.service.JuiceService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@NoArgsConstructor
@Slf4j
@Path("/juicerecipe")
public class JuiceResource {

    private JuiceService juiceService;

    @Inject
    protected JuiceResource(JuiceService juiceService) {
        this.juiceService = juiceService;
    }

    @POST
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response submitJuiceRecipe(JuiceRecipe juiceRecipe) {
        return Response.ok(juiceService.insertJuiceRecipe(juiceRecipe)).build();
    }

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJuiceRecipes() {
        return Response.ok(juiceService.getJuiceRecipes()).build();
    }

    @DELETE
    @Timed
    public Response clearJuiceRecipes() {
        juiceService.clearJuiceRecipes();
        return Response.noContent().build();
    }

}
