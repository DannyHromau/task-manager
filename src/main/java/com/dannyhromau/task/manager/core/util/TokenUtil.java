package com.dannyhromau.task.manager.core.util;

import com.dannyhromau.task.manager.exception.UnAuthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class TokenUtil {
    private static final String WRONG_AUTHENTICATION_MESSAGE = ErrorMessages.WRONG_AUTHENTICATION_MESSAGE.label;
    public UserDetails getUserDetails() {
        Optional<Jwt> jwtOpt = getJwtToken();
        UserDetails userDetails = new UserDetails();
        Jwt token = jwtOpt.orElseThrow(() -> new UnAuthorizedException(WRONG_AUTHENTICATION_MESSAGE));
        userDetails.setId(UUID.fromString(token.getClaim("id").toString()));
        userDetails.setEmail(token.getClaim("email"));
        return userDetails;
    }

    private Optional<Jwt> getJwtToken() {
        Authentication currentAuthentication = getAuthentication();
        Jwt token = currentAuthentication instanceof JwtAuthenticationToken ?
                ((JwtAuthenticationToken) currentAuthentication).getToken() : null;
        return token == null ? Optional.empty() : Optional.of(token);
    }

    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
