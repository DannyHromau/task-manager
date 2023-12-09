package com.dannyhromau.task.manager;

import com.dannyhromau.task.manager.core.security.config.AuthValidationConfig;
import com.dannyhromau.task.manager.core.security.config.SecurityUrlConfig;
import com.dannyhromau.task.manager.core.security.jwt.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({AuthValidationConfig.class, JwtConfig.class, SecurityUrlConfig.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
