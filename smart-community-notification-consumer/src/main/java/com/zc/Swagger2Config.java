package com.zc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
public class Swagger2Config {


    @Bean
    public Docket createRestApi(){
        List<Parameter> pars = new ArrayList<Parameter>();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zc"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("聚合API文档智慧社区消费者模块")
                .description("provider | smart_community.com")
                .termsOfServiceUrl("http://www.smart_community.com.com")
                .contact("http://www.smart_community.com.com")
                .version("1.0")
                .build();
    }


}
