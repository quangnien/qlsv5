package com.qlsv5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author NienNQ
 * @created 2023 - 03 - 25 2:51 PM
 * @project qlsv5
 */

@Document(collection = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {

    public static final String ROLE_PHONGGIAOVU = "ROLE_PHONGGIAOVU";
    public static final String ROLE_GIANGVIEN = "ROLE_GIANGVIEN";
    public static final String ROLE_SINHVIEN = "ROLE_SINHVIEN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Id
    private String id;
    private String name;

    @Override
    public String toString() {
        return "Role [name=" + name + "]" + "[id=" + id + "]";
    }
}