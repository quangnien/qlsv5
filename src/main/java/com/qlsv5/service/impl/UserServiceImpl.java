package com.qlsv5.service.impl;

import com.qlsv5.entity.UserEntity;
import com.qlsv5.service.impl.repository.UserRepository;
import com.qlsv5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public UserEntity findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
