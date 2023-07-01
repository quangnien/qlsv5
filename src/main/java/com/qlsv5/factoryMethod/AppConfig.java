package com.qlsv5.factoryMethod;

import com.qlsv5.factory.AdminChangePW;
import com.qlsv5.service.UserService;
import com.qlsv5.strategy.UpdatePasswordAdmin;
import com.qlsv5.validation.ValidatorAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * qlsv5
 *
 * @author Smartee
 * @date 7/1/2023 7:20 PM
 */
@Configuration
public class AppConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidatorAdmin validatorAdmin;

    @Autowired
    private PasswordEncoder encoder;

    @Bean
    public AdminChangePW updatePasswordAdmin() {
        AdminChangePW updatePasswordAdmin = new AdminChangePW();
        updatePasswordAdmin.setUserService(userService);
        updatePasswordAdmin.setValidatorAdmin(validatorAdmin);
        updatePasswordAdmin.setEncoder(encoder);
        return updatePasswordAdmin;
    }

    // Other bean definitions or configuration methods

}
