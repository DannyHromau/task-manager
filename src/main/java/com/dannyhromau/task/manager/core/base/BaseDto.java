package com.dannyhromau.task.manager.core.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public abstract class BaseDto implements Serializable {
    private UUID id;
}
