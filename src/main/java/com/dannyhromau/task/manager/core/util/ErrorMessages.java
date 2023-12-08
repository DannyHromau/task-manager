package com.dannyhromau.task.manager.core.util;

public enum ErrorMessages {
    INCORRECT_DATA_MESSAGE("Incorrect or duplicated data"),
    ENTITY_NOT_FOUND_MESSAGE("Entity with id : %s not exists"),
    WRONG_PASSWORD_MESSAGE("Wrong password format"),
    WRONG_EMAIL_MESSAGE("Wrong email format"),
    WRONG_AUTHENTICATION_MESSAGE("Cannot authorize : check input data or unblock the user"),
    WRONG_TOKEN_MESSAGE("Wrong token"),
    FORBIDDEN_STATUS_MESSAGE("Permission denied : no such authorities"),
    EXISTING_EMAIL_MESSAGE("User with email : %s already exists"),
    NULLABLE_ID_MESSAGE("The given id must not be null");
    public final String label;

    ErrorMessages(String label) {
        this.label = label;
    }
}
