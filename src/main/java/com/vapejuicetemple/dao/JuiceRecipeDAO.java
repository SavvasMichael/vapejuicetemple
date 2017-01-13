package com.vapejuicetemple.dao;

import com.vapejuicetemple.domain.JuiceRecipe;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JuiceRecipeDAO extends AbstractDAO<JuiceRecipe> {
    public JuiceRecipeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<JuiceRecipe> findAll() {
        return list(namedQuery("com.javaeeeee.dwstart.core.Employee.findAll"));
    }

    public Optional<JuiceRecipe> findById(UUID id) {
        return Optional.ofNullable(get(id));
    }
}
