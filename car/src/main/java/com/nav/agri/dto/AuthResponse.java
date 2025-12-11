package com.nav.agri.dto;

public record AuthResponse(String token, String username, Long expiresAt) {
}
