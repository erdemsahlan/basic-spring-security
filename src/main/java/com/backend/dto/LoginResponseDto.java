package com.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private long id;
    private String message;
    private String token;
    private String username;
    private String email;
    private String accessToken;
}
