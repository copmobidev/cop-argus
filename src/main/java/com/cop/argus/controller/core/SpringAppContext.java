package com.cop.argus.controller.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author chris.liu
 */
@Component
public class SpringAppContext implements ApplicationContextAware {

    private static ApplicationContext appContext;

    // Private constructor prevents instantiation from other classes
    private SpringAppContext() {

    }

    @Override
    public void setApplicationContext(ApplicationContext appContext)
            throws BeansException {
        SpringAppContext.appContext = appContext;

    }

    public static Object getBean(String beanName) {
        return appContext.getBean(beanName);
    }
}
