package com.qlsv5.service.impl;

import com.qlsv5.config.SignUpRequest;
import com.qlsv5.config.UserAlreadyExistAuthenticationException;
import com.qlsv5.entity.RoleEntity;
import com.qlsv5.entity.UserEntity;
import com.qlsv5.repository.RoleRepository;
import com.qlsv5.repository.UserRepository;
import com.qlsv5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author NienNQ
 * @created 2023 - 03 - 26 10:08 AM
 * @project qlsv5
 */
@Service
@RequiredArgsConstructor
@Lazy
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity findUserByEmail(final String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Override
    @Transactional(value = "transactionManager")
    public UserEntity registerNewUser(final SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
        if (userRepository.existsByEmailIgnoreCase(signUpRequest.getEmail())) {
            throw new UserAlreadyExistAuthenticationException("User with email id " + signUpRequest.getEmail() + " already exist");
        }
        UserEntity user = buildUser(signUpRequest);
        user = userRepository.save(user);
//        userRepository.flush();
        return user;
    }

    private UserEntity buildUser(final SignUpRequest signUpRequest) {
        UserEntity user = new UserEntity();
        user.setDisplayName(signUpRequest.getDisplayName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.addRole(roleRepository.findByName(RoleEntity.ROLE_USER));
        user.setEnabled(true);
        return user;
    }
}
