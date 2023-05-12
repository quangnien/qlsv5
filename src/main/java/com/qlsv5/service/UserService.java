package com.qlsv5.service;


import com.qlsv5.entity.UserEntity;

public interface UserService {
    UserEntity findByUsername(String username);
    UserEntity findById(String id);
    public UserEntity updateUser(UserEntity userEntity);
}
