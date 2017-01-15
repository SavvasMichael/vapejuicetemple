package com.vapejuicetemple.service;

import com.google.inject.Inject;
import com.vapejuicetemple.dao.JuiceRecipeDAO;
import com.vapejuicetemple.domain.JuiceRecipe;
import com.vapejuicetemple.exception.InvalidJuiceRecipeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JuiceService {
    private List<JuiceRecipe> juiceRecipes = new ArrayList<>();
    private JuiceRecipeDAO juiceRecipeDAO;

    @Inject
    public JuiceService(JuiceRecipeDAO juiceRecipeDAO) {
        this.juiceRecipeDAO = juiceRecipeDAO;
    }

    public JuiceRecipe insertJuiceRecipe(JuiceRecipe juiceRecipe) {
        if (validateJuiceRecipe(juiceRecipe)) {
           return juiceRecipeDAO.saveRecipe(juiceRecipe);
        }
        return null;
}
    public List<JuiceRecipe> getJuiceRecipes() {
        return juiceRecipeDAO.findAll();
    }

    public Optional<JuiceRecipe> getJuiceRecipeById(int id) {
        return juiceRecipeDAO.findById(id);
}

    public JuiceRecipe deleteJuiceRecipe(int id) {
        return juiceRecipeDAO.deleteRecipe(id);
    }

    private boolean validateJuiceRecipe(JuiceRecipe juiceRecipe) {
        if (juiceRecipe == null || juiceRecipe.getIngredients() == null) {
            throw new InvalidJuiceRecipeException("Juice recipe was null");
        }
        return true;
    }

}
