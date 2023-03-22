package com.qlsv5.api;

import com.qlsv5.constant.ApiPath;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NienNQ
 * @created 2023 - 03 - 22 10:43 AM
 * @project qlsv5
 */
@RestController
@OpenAPIDefinition(info = @Info(title = "logout-endpoint"))
@SecurityRequirement(name = "basicAuth")
@RequestMapping(value = ApiPath.API_ADMIN, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class LogoutApi {

    @PostMapping("logout")
    @Operation(description = "End authenticated user session")
    public void logout() {
        throw new UnsupportedOperationException();
    }
}
