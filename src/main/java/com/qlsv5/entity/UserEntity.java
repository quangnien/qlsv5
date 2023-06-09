package com.qlsv5.entity;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
  @Id
  private String id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  private String roleName;

  private String idLogin;
  private String userFullName;

  @DBRef
  private Set<RoleEntity> roles = new HashSet<>();

  public UserEntity(String username, String email, String password, String idLogin, String userFullName) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.idLogin = idLogin;
    this.userFullName = userFullName;
  }

  public UserEntity(String username, String email, String password, String idLogin) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.idLogin = idLogin;
  }
}
