package com.stackroute.restaurantownerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stackroute.restaurantownerservice"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        ApiInfo apiInfo = new ApiInfo(
                "ReserveOut Application",
                "An application to search restaurant from a restaurant repository by restaurant name, location, Cuisine",
                "ReserveOutApplication V1",
                "Terms of service",
                "reserveout@gmail.com",
                "License of API",
                "https://swagger.io/docs/");
        return apiInfo;
    }
}