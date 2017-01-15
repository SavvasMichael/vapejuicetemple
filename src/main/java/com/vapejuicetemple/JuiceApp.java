package com.vapejuicetemple;

import com.vapejuicetemple.dao.JuiceRecipeDAO;
import com.vapejuicetemple.domain.JuiceRecipe;
import com.vapejuicetemple.resource.JuiceResource;
import com.vapejuicetemple.service.JuiceService;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class JuiceApp extends Application<JuiceConfig> {

    public static void main(String[] args) throws Exception {
        new JuiceApp().run(args);
    }

    private final HibernateBundle<JuiceConfig> hibernate = new HibernateBundle<JuiceConfig>(JuiceRecipe.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(JuiceConfig juiceConfig) {
            return juiceConfig.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<JuiceConfig> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final JuiceConfig juiceConfig, Environment environment) throws Exception {
        final JuiceRecipeDAO juiceRecipeDAO = new JuiceRecipeDAO(hibernate.getSessionFactory());
        environment.jersey().register(new JuiceService(juiceRecipeDAO));
        JuiceService juiceService = new JuiceService(juiceRecipeDAO);
        environment.jersey().register(new JuiceResource(juiceService));
    }
}
