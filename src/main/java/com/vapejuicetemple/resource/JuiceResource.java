package com.vapejuicetemple.resource;

import com.codahale.metrics.annotation.Timed;
import com.vapejuicetemple.domain.JuiceRecipe;
import com.vapejuicetemple.service.JuiceService;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@NoArgsConstructor
@Slf4j
@Produces(MediaType.APPLICATION_JSON)
@Path("/juicerecipe")
public class JuiceResource {

    private JuiceService juiceService;

    @Inject
    public JuiceResource(JuiceService juiceService) {
        this.juiceService = juiceService;
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response saveJuiceRecipe(JuiceRecipe juiceRecipe) {
        return Response.ok(juiceService.insertJuiceRecipe(juiceRecipe)).build();
    }

    @GET
    @Timed
    @UnitOfWork
    public Response getJuiceRecipes() {
        return Response.ok(juiceService.getJuiceRecipes()).build();
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public Response getJuiceRecipe(@PathParam("id") int id) {
        return Response.ok(juiceService.getJuiceRecipeById(id)).build();
    }

    @DELETE
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public Response deleteJuiceRecipe(@PathParam("id") int id) {
        return Response.ok(juiceService.deleteJuiceRecipe(id)).build();
    }
}
