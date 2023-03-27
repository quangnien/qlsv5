package com.qlsv5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author NienNQ
 * @created 2023 - 03 - 25 2:50 PM
 * @project qlsv5
 */
@Document(collection = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private String id;

    @NotBlank(message = "Vui lòng nhập Email!")
    @Email(message = "Nhập đúng định dạng email!")
    private String email;

    @NotNull
    @NotBlank(message = "Vui Lòng Nhập Password")
    @Length(min = 3 , message = "Password chứa ít nhất 3 ký tự!")
    private String password;

    private String displayName;

    private boolean enabled;

    private boolean using2FA;

    private String secret;

    private Set<UserRoleEntity> roles = new HashSet<>();

    public void addRole(RoleEntity role) {
        UserRoleEntity userRole = new UserRoleEntity(this, role);
        roles.add(userRole);
    }

    public void removeRole(RoleEntity role) {
        for (Iterator<UserRoleEntity> iterator = roles.iterator(); iterator.hasNext();) {
            UserRoleEntity userRole = iterator.next();

            if (userRole.getUser().equals(this) && userRole.getRole().equals(role)) {
                iterator.remove();
                userRole.setUser(null);
                userRole.setRole(null);
            }
        }
    }
}
