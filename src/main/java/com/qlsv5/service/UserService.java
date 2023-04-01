package com.qlsv5.service;


import com.qlsv5.entity.UserEntity;

public interface UserService {
    UserEntity findByUsername(String username);
    public UserEntity updateUser(UserEntity userEntity);
}
