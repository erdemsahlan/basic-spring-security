package com.backend.security.dto;

import lombok.Data;

@Data
class AuthRequest {
    private String username;
    private String password;
}
