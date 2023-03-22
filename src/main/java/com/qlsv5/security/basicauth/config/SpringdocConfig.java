package com.qlsv5.security.basicauth.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;
/**
 * @author NienNQ
 * @created 2023 - 03 - 22 10:42 AM
 * @project qlsv5
 */


@Configuration
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "basicAuth",
        scheme = "basic")
public class SpringdocConfig {}
