package com.oop.eventticketingsystem.util.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * ApplicationContextHolder is a utility class to get the application context and beans.
 */
@Component
public class ApplicationContextHolder {

    private static ApplicationContext context;

    // Setter method called by Spring
    @Autowired
    public ApplicationContextHolder(ApplicationContext applicationContext) {
        ApplicationContextHolder.context = applicationContext;
    }

    // Static method to get the context
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    // Static method to get a bean
    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}

