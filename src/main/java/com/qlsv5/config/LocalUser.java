package com.qlsv5.config;

import lombok.Getter;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author NienNQ
 * @created 2023 - 03 - 25 2:46 PM
 * @project qlsv5
 */
@Getter
public class LocalUser extends org.springframework.security.core.userdetails.User {

    public LocalUser(final String userID, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired,
                     final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities) {
        super(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
