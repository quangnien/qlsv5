package com.qlsv5.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NienNQ
 * @created 2023 - 03 - 30 9:43 AM
 * @project qlsv5
 */
@Document(collection = "token_refresh_token_pair")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenRefreshTokenPairEntity {

    @Id
    private String id;

    private String jti;

    private String token;

    private String refreshToken;

}