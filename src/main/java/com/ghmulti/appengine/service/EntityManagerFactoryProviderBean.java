package com.ghmulti.appengine.service;

import com.google.appengine.api.utils.SystemProperty;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class EntityManagerFactoryProviderBean {

    public EntityManagerFactory createInstance() {
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
