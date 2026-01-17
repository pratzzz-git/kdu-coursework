package org.example.library.api.error;

import java.time.Instant;
import java.util.List;

public class ApiErrorResponse {

    private Instant timestamp;
    private String path;
    private String errorCode;
    private String message;
    private List<ApiErrorDetail> details;
    private String correlationId;

    // getters & setters
}
