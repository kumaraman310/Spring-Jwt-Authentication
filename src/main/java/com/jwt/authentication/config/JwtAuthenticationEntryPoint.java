package com.jwt.authentication.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        // Set content type
        response.setContentType("application/json");

        Integer status = 0;
        String error = null;
        if (response.getStatus() == 403) {
            status = 403;
            error = HttpStatus.FORBIDDEN.getReasonPhrase();
        } else {
            status = HttpStatus.UNAUTHORIZED.value();
            error = HttpStatus.UNAUTHORIZED.getReasonPhrase();
        }

        // Create error response body
        PrintWriter writer = response.getWriter();
        writer.println("{");
        writer.println("\"timeStamp\": \"" + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + "\",");
        writer.println("\"status\": " + status + ",");
        writer.println("\"error\": \"" + error + "\",");
        writer.println("\"message\": \"" + getErrorMessage(authException) + "\",");
        writer.println("\"path\": \"" + request.getRequestURI() + "\"");
        writer.println("}");
    }

    private String getErrorMessage(AuthenticationException authException) {
        if (authException instanceof BadCredentialsException) {
            return "Invalid Credential !!";
        }
        return authException.getMessage();
    }


}
