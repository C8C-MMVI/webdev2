package com.nav.car;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@org.springframework.context.annotation.Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home").permitAll() // public
                        .anyRequest().authenticated()             // secure everything else
                )
                .formLogin(login -> login
                        .defaultSuccessUrl("/secure", true) // redirect after login
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}
