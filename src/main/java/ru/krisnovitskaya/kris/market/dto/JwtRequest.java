package ru.krisnovitskaya.kris.market.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
