package org.example.library.web.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.users")
@Getter
@Setter
public class SecurityUsersProperties {

    private String librarianUsername;
    private String librarianPassword;

    private String adminUsername;
    private String adminPassword;
}
