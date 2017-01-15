package com.vapejuicetemple.dao;

import com.vapejuicetemple.domain.JuiceRecipe;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class JuiceRecipeDAO extends AbstractDAO<JuiceRecipe> {
    public JuiceRecipeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<JuiceRecipe> findAll() {
        return list(namedQuery("findsAll"));
    }

    public Optional<JuiceRecipe> findById(int id) {
        return Optional.ofNullable(get(id));
    }

    public JuiceRecipe saveRecipe(JuiceRecipe juiceRecipe) {
        return persist(juiceRecipe);
    }

    public JuiceRecipe deleteRecipe(int id) {
        Optional<JuiceRecipe> byId = findById(id);
        if(byId.isPresent()){
           currentSession().delete(byId.get());
            return byId.get();
        }
        return null;
    }
}
