package com.de.controlreclamostransporte.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class CustomAuthenticationFailureHandler  implements AuthenticationFailureHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler .class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        logger.error("Error en autenticación: ", exception);

        String errorMessage = "Nombre de usuario o contraseña incorrectos.";

        if (exception instanceof LockedException) {
            errorMessage = "Tu cuenta ha sido bloqueada.";
        } else if (exception instanceof DisabledException) {
            errorMessage = "Tu cuenta está deshabilitada.";
        } else if (exception instanceof AccountExpiredException) {
            errorMessage = "Tu cuenta ha expirado.";
        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage = "Tu contraseña ha expirado.";
        }

        response.sendRedirect("/login?error=true&message=" + errorMessage);
    }
}
