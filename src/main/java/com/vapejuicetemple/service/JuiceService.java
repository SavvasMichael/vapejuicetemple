package com.vapejuicetemple.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.vapejuicetemple.dao.JuiceRecipeDAO;
import com.vapejuicetemple.domain.JuiceRecipe;
import com.vapejuicetemple.exception.InvalidJuiceRecipeException;

import java.util.List;
import java.util.Optional;

@Singleton
public class JuiceService {
    private JuiceRecipeDAO juiceRecipeDAO;

    @Inject
    public JuiceService(JuiceRecipeDAO juiceRecipeDAO) {
        this.juiceRecipeDAO = juiceRecipeDAO;
    }

    public JuiceRecipe insertJuiceRecipe(JuiceRecipe juiceRecipe) {
        if (validateJuiceRecipe(juiceRecipe)) {
           return juiceRecipeDAO.saveJuiceRecipe(juiceRecipe);
        }
        return null;
}
    public List<JuiceRecipe> getJuiceRecipes() {
        return juiceRecipeDAO.getAllJuiceRecipes();
    }

    public Optional<JuiceRecipe> getJuiceRecipeById(int id) {
        return juiceRecipeDAO.getJuiceRecipeById(id);
}

    public JuiceRecipe deleteJuiceRecipe(int id) {
        return juiceRecipeDAO.deleteJuiceRecipe(id);
    }

    private boolean validateJuiceRecipe(JuiceRecipe juiceRecipe) {
        if (juiceRecipe == null || juiceRecipe.getIngredients() == null) {
            throw new InvalidJuiceRecipeException("Juice recipe was null");
        }
        return true;
    }

}
