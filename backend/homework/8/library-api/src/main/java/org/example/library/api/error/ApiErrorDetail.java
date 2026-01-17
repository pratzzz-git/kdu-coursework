package org.example.library.api.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorDetail {

    private String field;
    private String issue;
}
