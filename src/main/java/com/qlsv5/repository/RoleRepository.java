package com.qlsv5.repository;

import com.qlsv5.entity.ERole;
import com.qlsv5.entity.RoleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<RoleEntity, String> {
  Optional<RoleEntity> findByName(ERole name);
}
