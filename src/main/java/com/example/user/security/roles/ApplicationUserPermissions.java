package com.example.user.security.roles;

import lombok.Getter;

@Getter
public enum ApplicationUserPermissions {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    STOCK_READ("stock:read"),
    STOCK_WRITE("stock:write");

    private final String permission;

    ApplicationUserPermissions(String permission) {
        this.permission = permission;
    }

}
