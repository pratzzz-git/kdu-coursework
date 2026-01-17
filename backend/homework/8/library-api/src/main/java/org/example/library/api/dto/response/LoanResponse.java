package org.example.library.api.dto.response;

import java.time.Instant;
import java.util.UUID;

public class LoanResponse {

    private UUID id;
    private UUID bookId;
    private UUID borrowerId;
    private Instant borrowedAt;
    private Instant returnedAt;

    // getters & setters
}
