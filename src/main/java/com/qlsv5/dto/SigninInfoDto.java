package com.qlsv5.dto;

import com.qlsv5.security.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author NienNQ
 * @created 2023 - 03 - 29 3:33 PM
 * @project qlsv5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninInfoDto {
    String jwt;
    UserDetailsImpl userDetails;
    List<String> roles;
}