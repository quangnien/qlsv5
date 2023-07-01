//package com.qlsv5.config;
//
//import com.qlsv5.entity.UserEntity;
//import com.qlsv5.service.UserService;
//import com.qlsv5.validation.ValidatorAdmin;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
///**
// * qlsv5
// *
// * @author Smartee
// * @date 7/1/2023 3:58 PM
// */
//
//public class ApplicationConfig {
//
//    @Bean
//    public UserService userService() {
//        return new UserService() {
//            @Override
//            public UserEntity findByUsername(String username) {
//                return null;
//            }
//
//            @Override
//            public UserEntity findById(String id) {
//                return null;
//            }
//
//            @Override
//            public UserEntity updateUser(UserEntity userEntity) {
//                return null;
//            }
//        };
//    }
//
//    @Bean
//    public ValidatorAdmin validatorAdmin() {
//        return new ValidatorAdmin();
//    }
//}
