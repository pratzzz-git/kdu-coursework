package org.example.library.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateBookRequest {

    @NotBlank
    @Size(min = 2)
    private String title;

    // getters & setters
}
