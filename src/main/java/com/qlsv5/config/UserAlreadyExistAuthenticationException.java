package com.qlsv5.config;

import org.springframework.security.core.AuthenticationException;
/**
 * @author NienNQ
 * @created 2023 - 03 - 26 10:06 AM
 * @project qlsv5
 */
public class UserAlreadyExistAuthenticationException extends AuthenticationException {
    public UserAlreadyExistAuthenticationException(final String msg) {
        super(msg);
    }
}
