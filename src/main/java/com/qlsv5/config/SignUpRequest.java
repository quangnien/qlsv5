package com.qlsv5.config;

import com.qlsv5.utils.PasswordMatches;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author NienNQ
 * @created 2023 - 03 - 26 9:58 AM
 * @project qlsv5
 */
@Data
@PasswordMatches
public class SignUpRequest {
    @NotEmpty
    private String displayName;

    @NotEmpty
    private String email;

    @Size(min = 6, message = "Minimum 6 chars required")
    private String password;

    @NotEmpty
    private String matchingPassword;

    private boolean using2FA;

    public SignUpRequest(String displayName, String email, String password, String matchingPassword) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

}
