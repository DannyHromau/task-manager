package com.dannyhromau.task.manager.core.util;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDetails {
    private UUID id;
    private String email;
}
