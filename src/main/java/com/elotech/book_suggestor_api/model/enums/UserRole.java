package com.elotech.book_suggestor_api.model.enums;

import lombok.Getter;

@Getter
public enum UserRole {

    DEFAULT("default");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

}
