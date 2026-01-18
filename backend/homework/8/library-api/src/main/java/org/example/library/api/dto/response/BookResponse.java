package org.example.library.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class BookResponse {
    private UUID id;
    private String title;
    private String status;
    private Instant createdAt;
}
