package com.qlsv5.repository;

import com.qlsv5.entity.MonHocEntity;
import com.qlsv5.entity.RoleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author NienNQ
 * @created 2023 - 03 - 26 10:09 AM
 * @project qlsv5
 */
public interface RoleRepository extends MongoRepository<RoleEntity, String> {
    RoleEntity findByName(String name);
}
