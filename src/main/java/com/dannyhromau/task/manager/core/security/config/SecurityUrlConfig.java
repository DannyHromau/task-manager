package com.dannyhromau.task.manager.core.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;

@Getter
@Setter
@ConfigurationProperties(prefix = "securlpattern")
public class SecurityUrlConfig {
    private ArrayList<String> urls;
}
