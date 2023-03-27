package com.qlsv5.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NienNQ
 * @created 2023 - 03 - 26 9:54 AM
 * @project qlsv5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String accessToken;
}
