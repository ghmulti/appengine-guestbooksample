package com.ghmulti.appengine.context;

import org.springframework.context.MessageSource;
        import org.springframework.context.annotation.*;
        import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
        import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@ComponentScan(basePackages = { "com.ghmulti.appengine.service" })
@Import({PersistenceContext.class})
@PropertySource("classpath:application.properties")
public class RootApplicationContext {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename("i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
