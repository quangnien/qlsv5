package com.qlsv5.service;


import com.qlsv5.entity.UserEntity;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    UserEntity findByUsername(String username);
    UserEntity findById(String id);
    public UserEntity updateUser(UserEntity userEntity);
}
