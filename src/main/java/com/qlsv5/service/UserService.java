package com.qlsv5.service;

import com.qlsv5.config.SignUpRequest;
import com.qlsv5.config.UserAlreadyExistAuthenticationException;
import com.qlsv5.entity.UserEntity;

/**
 * @author NienNQ
 * @created 2023 - 03 - 25 2:49 PM
 * @project qlsv5
 */
public interface UserService {

    UserEntity findUserByEmail(String email);

    UserEntity registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

}
