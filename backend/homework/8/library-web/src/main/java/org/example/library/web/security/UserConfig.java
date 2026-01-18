package org.example.library.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {

    @Bean
    public UserDetailsService userDetailsService(
            SecurityUsersProperties props
    ) {
        return new InMemoryUserDetailsManager(
                User.withUsername(props.getLibrarianUsername())
                        .password(props.getLibrarianPassword())
                        .roles("LIBRARIAN")
                        .build(),

                User.withUsername(props.getAdminUsername())
                        .password(props.getAdminPassword())
                        .roles("ADMIN")
                        .build()
        );
    }
}
