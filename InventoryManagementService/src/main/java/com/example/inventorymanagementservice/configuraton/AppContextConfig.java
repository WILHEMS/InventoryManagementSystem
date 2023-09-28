package com.example.inventorymanagementservice.configuraton;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.example.inventorymanagementservice"})
@EntityScan(basePackages = {"com.example.inventorymanagementservice.components.persistence.entities"})
@EnableJpaRepositories(basePackages = {"com.example.inventorymanagementservice.components.persistence.repositories"})
@EnableHypermediaSupport(type = {EnableHypermediaSupport.HypermediaType.HAL, EnableHypermediaSupport.HypermediaType.COLLECTION_JSON})
public class AppContextConfig {
}
