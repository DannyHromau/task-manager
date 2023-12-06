package com.dannyhromau.task.manager.core.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private int accessExpiration;
    private int refreshExpiration;
    private String algorithm;
}
