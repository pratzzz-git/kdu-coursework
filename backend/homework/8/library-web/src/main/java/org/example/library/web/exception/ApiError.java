package org.example.library.web.exception;

import java.time.Instant;

public record ApiError(
        Instant timestamp,
        int status,
        String message
) {}
