package com.backend.security;

import lombok.Data;

@Data
class AuthRequest {
    private String username;
    private String password;
}
