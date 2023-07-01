//package com.qlsv5.factory;
//
//import com.qlsv5.service.UserService;
//import com.qlsv5.strategy.UpdatePasswordAdmin;
//import com.qlsv5.validation.ValidatorAdmin;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * qlsv5
// *
// * @author Smartee
// * @date 7/1/2023 7:20 PM
// */
//@Configuration
//public class AppConfig {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private ValidatorAdmin validatorAdmin;
//
//    @Autowired
//    private PasswordEncoder encoder;
//
//    public AdminChangePW adminChangePW() {
//        AdminChangePW adminChangePW = new AdminChangePW();
//        AdminChangePW.setUserService(userService);
//        AdminChangePW.setValidatorAdmin(validatorAdmin);
//        AdminChangePW.setEncoder(encoder);
//        return adminChangePW;
//    }
//
//    // Other bean definitions or configuration methods
//
//}
