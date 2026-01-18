package org.example.library.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookRequest {
    @NotBlank
    private String title;
}
