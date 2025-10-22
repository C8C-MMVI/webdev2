package com.nav.car.dto;

public record AuthResponse(String token, String username, Long expiresAt) {
}
