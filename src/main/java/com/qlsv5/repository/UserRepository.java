package com.qlsv5.repository;

import com.qlsv5.entity.RoleEntity;
import com.qlsv5.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author NienNQ
 * @created 2023 - 03 - 26 10:20 AM
 * @project qlsv5
 */
public interface UserRepository  extends MongoRepository<UserEntity, String> {
    UserEntity findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
}