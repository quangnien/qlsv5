//package com.qlsv5.swagger;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.builders.ResponseMessageBuilder;
//import springfox.documentation.schema.ModelRef;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Arrays;
//import java.util.Collections;
//
///**
// * @author NienNQ
// * @created 2023 - 03 - 05 3:55 PM
// * @project qlsv
// */
//@Configuration
//@EnableSwagger2
//@EnableWebMvc
////@Import(BeanValidatorPluginsConfiguration.class)
//public class SwaggerConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/api/*").addResourceLocations("com.qlsv5.api");
//        registry
//                .addResourceHandler(    "swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry
//                .addResourceHandler("/api/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//
//    @Bean
//    public Docket filterAPI() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.qlsv5.api"))
//                .paths(PathSelectors.ant("/api/*"))
//                .build()
//                .apiInfo(apiInfo())
//                .useDefaultResponseMessages(false)
//                .globalResponseMessage(RequestMethod.GET,
//                        Arrays.asList(new ResponseMessageBuilder()
//                                        .code(500)
//                                        .message("500 message custom")
//                                        .responseModel(new ModelRef("Error"))
//                                        .build(),
//                                new ResponseMessageBuilder()
//                                        .code(403)
//                                        .message("403 message custom!")
//                                        .build()));
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfo(
//                "Swagger API DEMO",
//                "Some custom description of API.",
//                "API TOS",
//                "Terms of service",
//                new Contact("Deft", "http://shareprogramming.net/", "quangnien24@gmail.com"),
//                "License of API", "API license URL", Collections.emptyList());
//    }
//
////    @Bean
////    public Docket api() {
////        return new Docket(DocumentationType.SWAGGER_2)
////                .select()
////                .apis(RequestHandlerSelectors.any())
////                .paths(PathSelectors.any())
////                .build();
////    }
//
////    @Bean
////    public Docket api() {
////        return new Docket(DocumentationType.SWAGGER_2)
////                .select()
////                .apis(RequestHandlerSelectors.basePackage("com.qlsv.api"))
////                .paths(PathSelectors.any())
////                .build()
////                .apiInfo(apiInfo());
////    }
//
////    @Bean
////    public Docket api() {
////        return new Docket(DocumentationType.SWAGGER_2)
////                .select()
////                .apis(RequestHandlerSelectors.any())
////                .paths(PathSelectors.any())
////                .build();
////    }
//
////    private ApiInfo apiInfo() {
////        return new ApiInfo(
////                "My REST API",
////                "Some custom description of API.",
////                "API TOS",
////                "Terms of service",
////                new Contact("NienNQ", "https://www.facebook.com/quangnien0911/", "quangnien24@gmail.com"),
////                "License of API", "API license URL", Collections.emptyList());
////    }
//}
