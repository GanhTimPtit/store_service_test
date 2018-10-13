package com.ptit.edu.store.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postsApi() {
        ArrayList<ResponseMessage> globalResponseMessage = new ArrayList<>();
        globalResponseMessage.add(new ResponseMessageBuilder()
                .code(500)
                .message("Internal server error")
                .build());

        return new Docket(DocumentationType.SWAGGER_2)/*.configure(new DocumentationContextBuilder().)*/
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ptit.edu.store"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, globalResponseMessage);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Store service API")
                .description("Store service API reference")
                .termsOfServiceUrl("http://store-service.com")
                .contact("anhthuy-mobile@gmail.com").license("Kidd License")
                .licenseUrl("anhthuy-mobile@gmail.com").version("1.0")
                .build();
    }
}
