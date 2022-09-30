package com.stackroute.paymentservice.swaggerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stackroute.paymentservice"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

        private ApiInfo apiInfo(){
        ApiInfo apiInfo = new ApiInfo(
                "ReserveOut Application",
                "An application to search restaurant from a restaurant repository by restaurant name, location, Cuisine and Book your table",
                "ReserveOutApplication V1",
                "Terms of service",
                "reserveout@gmail.com",
                "License of API",
                "https://swagger.io/docs/");
        return apiInfo;
        }
}
