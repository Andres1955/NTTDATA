package com.example.demo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LoggingFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Log del request (opcional)
        logger.info("Request: {} {}", request.getMethod(), request.getRequestURI());

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);

        // Log del response (código HTTP)
        logger.info("Response: {} - Status: {}",
                request.getRequestURI(),
                response.getStatus());
    }
}