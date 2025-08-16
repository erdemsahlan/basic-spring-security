package com.backend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@Component
public class GlobalRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
//        Enumeration<String> headerNames = request.getHeaderNames();
        String url = request.getRequestURI();
//        System.out.println("📨 [Headers]");
//
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            String headerValue = request.getHeader(headerName);
//            System.out.println("🔑 " + headerName + ": " + headerValue);
//        }
//
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader != null) {
//            System.out.println("🔐 Authorization: " + authHeader);
//        }
        filterChain.doFilter(request, response);    }
}