package com.mtech.security.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(int status, String message, LocalDateTime timestamp) {
}
