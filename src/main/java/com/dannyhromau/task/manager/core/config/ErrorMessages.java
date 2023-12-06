package com.dannyhromau.task.manager.core.config;

public enum ErrorMessages {
    INCORRECT_DATA_MESSAGE("Incorrect or duplicated data"),
    ENTITY_NOT_FOUND_MESSAGE("Entity with id : %s not exists"),
    WRONG_PASSWORD_MESSAGE("Wrong password format"),
    WRONG_EMAIL_MESSAGE("Wrong email format"),
    DUPLICATE_USER_MESSAGE("The given user already exists"),
    WRONG_AUTHENTICATION_MESSAGE("Cannot authorize : check input data or unblock the user"),
    WRONG_TOKEN_MESSAGE("Wrong token"),
    NULLABLE_ID_MESSAGE("The given id must not be null");
    public final String label;

    ErrorMessages(String label) {
        this.label = label;
    }
}
