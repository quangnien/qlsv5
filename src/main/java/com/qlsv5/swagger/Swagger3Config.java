//package com.qlsv5.swagger;
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import io.swagger.v3.oas.models.servers.Server;
//@Configuration
//public class Swagger3Config {
//
//    @Value("${bezkoder.openapi.dev-url}")
//    private String devUrl;
//
//    @Value("${bezkoder.openapi.prod-url}")
//    private String prodUrl;
//
//    @Bean
//    public OpenAPI myOpenAPI() {
//        Server devServer = new Server();
//        devServer.setUrl(devUrl);
//        devServer.setDescription("Server URL in Development environment");
//
//        Server prodServer = new Server();
//        prodServer.setUrl(prodUrl);
//        prodServer.setDescription("Server URL in Production environment");
//
//        Contact contact = new Contact();
//        contact.setEmail("quangnien24@gmail.com");
//        contact.setName("NienNQ");
//        contact.setUrl("https://www.facebook.com/quangnien0911");
//
//        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
//
//        Info info = new Info()
//                .title("Student Management API")
//                .version("1.0")
//                .contact(contact)
//                .description("This API exposes endpoints to manage student.").termsOfService("https://www.facebook.com/quangnien0911")
//                .license(mitLicense);
//
//        return new OpenAPI().info(info).servers(Arrays.asList(devServer, prodServer));
//    }
//
//    @Bean
//    public OpenAPI customizeOpenAPI() {
//        //@formatter:off
//        final String securitySchemeName = "bearerAuth";
//        return new OpenAPI()
//                .addSecurityItem(new SecurityRequirement()
//                        .addList(securitySchemeName))
//                .components(new Components()
//                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
//                                .name(securitySchemeName)
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("bearer")
//                                .description(
//                                        "Provide the JWT token. JWT token can be obtained from the Login API. For testing, use the credentials <strong>john/password</strong>")
//                                .bearerFormat("JWT")));
//        //@formatter:on
//
//    }
//}