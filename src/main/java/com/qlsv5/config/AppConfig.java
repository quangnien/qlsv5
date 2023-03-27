package com.qlsv5.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.interfaces.RSAPublicKey;

/**
 * @author NienNQ
 * @created 2023 - 03 - 27 10:10 AM
 * @project qlsv5
 */
@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public JwtEncoder jwtEncoder() {
//        Key key = new SecretKeySpec("secret".getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());
//        return new NimbusJwtEncoder((JWKSource<SecurityContext>) key);
//    }

//    @Bean
////    public JwtEncoder jwtEncoder() {
//    public Jwt encode(JwtEncoderParameters parameters) throws JwtEncodingException {
//        Assert.notNull(parameters, "parameters cannot be null");
//        JwsHeader headers = parameters.getJwsHeader();
//        if (headers == null) {
//            headers = DEFAULT_JWS_HEADER;
//        }
//
//        JwtClaimsSet claims = parameters.getClaims();
//        JWK jwk = this.selectJwk(headers);
//        headers = addKeyIdentifierHeadersIfNecessary(headers, jwk);
//        String jws = this.serialize(headers, claims, jwk);
//        return new Jwt(jws, claims.getIssuedAt(), claims.getExpiresAt(), headers.getHeaders(), claims.getClaims());
//    }

//    @Bean
//    public JwtEncoder jwtEncoder() {
//        return NimbusJwtEncoder.withSecretKey(new SecretKeySpec("yourSecretKey".getBytes(), "HmacSHA256")).build();
//    }

//    @Bean
//    public JwtEncoder jwtEncoder() {
//        byte[] keyBytes = "yourSecretKey".getBytes(StandardCharsets.UTF_8);
//        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
//
//        JwtEncoder encoder = new NimbusJwtEncoder((JWKSource<SecurityContext>) key);
//
//        return encoder;
//    }
}