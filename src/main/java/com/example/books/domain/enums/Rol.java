package com.example.books.domain.enums;

public enum Rol {

    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private int code;
    private String description;

    private Rol(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Rol toEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (Rol role : Rol.values()) {
            if (code.equals(role.getCode())) return role;
        }
        throw new IllegalArgumentException("Invalid Code: " + code);
    }
}
