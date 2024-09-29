package com.Event.Event.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtFilter jwtFilter = new JwtFilter();
        jwtFilter.setAuthenticationManager(authenticationManager());

        http
            .csrf().disable()
            .cors().and() // Enable CORS support
            .authorizeRequests()
            .antMatchers("/api/users/register", "/api/users/login").permitAll()
            .antMatchers("/api/users/reset-password", "/api/users/forgot-password").permitAll() // Allow access to reset password and forgot password endpoints
            .antMatchers("/admin/**").hasRole("ADMIN") // Admin-specific routes
            .antMatchers("/user/**").hasAnyRole("USER", "ADMIN") // Allow both for USER & ADMIN
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter before the default filter
            .formLogin().disable()
            .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
