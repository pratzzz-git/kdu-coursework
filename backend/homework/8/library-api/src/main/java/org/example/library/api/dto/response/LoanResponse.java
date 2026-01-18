package org.example.library.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class LoanResponse {
    private UUID loanId;
    private UUID bookId;
    private Instant borrowedAt;
    private Instant returnedAt;
}
