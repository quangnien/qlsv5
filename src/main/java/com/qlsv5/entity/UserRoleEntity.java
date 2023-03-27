package com.qlsv5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * @author NienNQ
 * @created 2023 - 03 - 25 2:51 PM
 * @project qlsv5
 */
@Document(collection = "user_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleEntity {

    @Id
    private String id;

    private UserEntity user;
    private RoleEntity role;

    protected boolean deleted;

//    private UserRolePK id;

    private String userId;
    private String roleId;


    public UserRoleEntity(UserEntity userEntity, RoleEntity role) {
        this.role = role;
        this.user = user;
    }
}
