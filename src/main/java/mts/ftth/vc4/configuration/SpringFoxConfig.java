package mts.ftth.vc4.configuration;

import java.util.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.builders.PathSelectors;*/

//@Configuration
public class SpringFoxConfig {
	
	/* public static final String AUTHORIZATION_HEADER = "Authorization";

	    private ApiKey apiKey(){
	        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	    }

	    private ApiInfo apiInfo(){
	        return new ApiInfo(
	                "Spring Boot Blog REST APIs",
	                "Spring Boot Blog REST API Documentation",
	                "1",
	                "Terms of service",
	                new Contact("Ramesh Fadatare", "www.javaguides.net", "ramesh@gmail.com"),
	                "License of API",
	                "API license URL",
	                Collections.emptyList()
	        );
	    }

	    @Bean
	    public Docket api(){
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .securityContexts(Arrays.asList(securityContext()))
	                .securitySchemes(Arrays.asList(apiKey()))
	                .select()
	                .apis(RequestHandlerSelectors.any())
	                .paths(PathSelectors.any())
	                .build();
	    }

	    private SecurityContext securityContext(){
	        return SecurityContext.builder().securityReferences(defaultAuth()).build();
	    }

	    private List<SecurityReference> defaultAuth(){
	        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
	        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	        authorizationScopes[0] = authorizationScope;
	        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	    }*/
}