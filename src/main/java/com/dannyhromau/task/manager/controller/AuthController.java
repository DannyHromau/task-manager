package com.dannyhromau.task.manager.controller;

import com.dannyhromau.task.manager.api.dto.AuthDto;
import com.dannyhromau.task.manager.api.dto.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authorization service", description = "Authorization controller")
@ApiResponse(responseCode = "200", description = "Successful operation")
@ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
@ApiResponse(responseCode = "404", description = "Not found")
@ApiResponse(responseCode = "401", description = "Unauthorized")
@ApiResponse(responseCode = "503", description = "Service unavailable")
public interface AuthController {

    @PostMapping("/register")
    @Operation(description = "register user")
    ResponseEntity<Boolean> register(AuthDto authDto);

    @PostMapping("/login")
    @Operation(description = "authorize user")
    ResponseEntity<TokenDto> login(AuthDto authDto);

    @PutMapping("/refresh")
    @Operation(description = "refresh token")
    ResponseEntity<TokenDto> refresh(TokenDto tokenDto);
}
