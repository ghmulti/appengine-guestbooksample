package com.ghmulti.appengine.context;

import com.google.appengine.api.utils.SystemProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = { "com.ghmulti.appengine.repository" })
@EnableTransactionManagement
public class PersistenceContext {

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        Map<String, String> properties = new HashMap();
        if (SystemProperty.environment.value() ==
                SystemProperty.Environment.Value.Production) {
                    properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.GoogleDriver");
                    properties.put("javax.persistence.jdbc.url", "jdbc:google:mysql://perfect-trilogy-863:caf");
        } else {
            properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
            properties.put("javax.persistence.jdbc.url", "jdbc:mysql://127.0.0.1:3306/appengine");
            properties.put("javax.persistence.jdbc.user", "multi");
            properties.put("javax.persistence.jdbc.password", "multi");
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("caf", properties);

        return emf;
    }
}
