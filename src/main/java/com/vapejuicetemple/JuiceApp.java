package com.vapejuicetemple;

import com.vapejuicetemple.dao.JuiceRecipeDAOConfiguration;
import com.vapejuicetemple.domain.JuiceRecipe;
import com.vapejuicetemple.resource.JuiceResource;
import com.vapejuicetemple.service.JuiceService;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Environment;

public class JuiceApp extends Application<JuiceConfig> {
    public static void main(String[] args) throws Exception {
        new JuiceApp().run(args);
    }

    private final HibernateBundle<JuiceRecipeDAOConfiguration> hibernateBundle
            = new HibernateBundle<JuiceRecipeDAOConfiguration>(
            JuiceRecipe.class
    ) {
        @Override
        public DataSourceFactory getDataSourceFactory(
                JuiceRecipeDAOConfiguration configuration
        ) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void run(JuiceConfig juiceConfig, Environment environment) throws Exception {
        JuiceResource juiceResource = new JuiceResource(new JuiceService()) {
        };
        environment.jersey().register(juiceResource);
    }
}
