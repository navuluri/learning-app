package com.learning.app;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This class configures the Swagger Api for Spring Framework.
 * <p>The class parses the mentioned packages and generates Swagger based UI for the REST APIs</p>
 * <p>After successful application startup, open a browser to the below url to open the swagger documentation: </p>
 * <pre>http://HOST:PORT/swagger-ui</pre>
 *
 * <p>Once the Swagger-UI is displayed, it provides all the documentation of the RESTful APIs. The APIs can be tested using this swagger-ui</p>
 *
 * @author Bhaskara Navuluri
 * @since 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration
{
    /**
     * Prepares the Docket plugin with minimal Swagger 2 configurations
     *
     * @return a Docket documentation plugin with documentation type as Swagger v2.0 instance
     */
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.learning.app.api"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiEndPointsInfo());
    }


    /**
     * Builds API Information like Author, Description, URL, License Info etc on the Swagger UI
     *
     * @return a API Info that can be displayed on the Swagger-UI
     */
    private ApiInfo apiEndPointsInfo()
    {
        return new ApiInfoBuilder().title("Learning App")
                .description("Learning App REST API - The API for accessing the Learning Courses")
                .contact(new Contact("Bhaskara", "https://stackoverflow.com/users/1781174/bhaskara", "bhaskara.navuluri@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0")
                .build();

    }
}
