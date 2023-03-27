package com.qlsv5.config;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author NienNQ
 * @created 2023 - 03 - 25 2:45 PM
 * @project qlsv5
 */

@Data
@AllArgsConstructor
public class LoginRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
