package com.ghmulti.appengine.config;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Inject;
import javax.servlet.ServletContext;

public class JerseyConfiguration extends ResourceConfig {

    @Inject
    public JerseyConfiguration(ServiceLocator serviceLocator, ServletContext servletContext) {
    }


}
