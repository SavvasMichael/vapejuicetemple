package com.vapejuicetemple.service;

import com.google.inject.Inject;
import com.vapejuicetemple.dao.JuiceRecipeDAO;
import com.vapejuicetemple.domain.JuiceRecipe;
import com.vapejuicetemple.exception.InvalidJuiceRecipeException;
import io.dropwizard.hibernate.UnitOfWork;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JuiceService {
    private List<JuiceRecipe> juiceRecipes = new ArrayList<>();
    private JuiceRecipeDAO juiceRecipeDAO;

    @Inject
    public JuiceService(JuiceRecipeDAO juiceRecipeDAO) {
        this.juiceRecipeDAO = juiceRecipeDAO;
    }

    public JuiceService() {
    }

    public List<JuiceRecipe> insertJuiceRecipe(JuiceRecipe juiceRecipe) {
        if (validateJuiceRecipe(juiceRecipe)) {
            juiceRecipes.add(juiceRecipe);
            return juiceRecipes;
        }
        return null;
    }

    public List<JuiceRecipe> getJuiceRecipes() {
        return juiceRecipes;
    }

    @UnitOfWork
    public Optional<JuiceRecipe> getJuiceRecipeById(UUID id) {
        return juiceRecipeDAO.findById(id);
    }

    public void clearJuiceRecipes() {
        juiceRecipes.clear();
    }

    private boolean validateJuiceRecipe(JuiceRecipe juiceRecipe) {
        if (juiceRecipe == null || juiceRecipe.getIngredients() == null) {
            throw new InvalidJuiceRecipeException("Juice recipe was null");
        }
        return true;
    }
}
