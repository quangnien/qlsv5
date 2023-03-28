package com.qlsv5.repository;

import com.qlsv5.entity.ERole;
import com.qlsv5.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
