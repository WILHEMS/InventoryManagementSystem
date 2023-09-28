package com.example.inventorymanagementservice.configuraton;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class WebMvcConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {AppContextConfig.class,};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
