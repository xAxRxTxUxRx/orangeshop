package com.artur.orange_backend.model.utils.user;

public enum UserRole {
    ADMIN("ADMIN_ROLE"),
    USER("USER_ROLE");

    UserRole(String name){
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }
}
