package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.jwt.JwtAuthFilter;
import com.example.demo.repositories.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository =  userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        //.sessionManagement()
        //.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Disable sessions
        //.and()
        .csrf().disable()
        .addFilterAfter(new JwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers("/api/public/**").permitAll() // Allow access to public endpoints
        .anyRequest().authenticated() // Requires authentication for other endpoints
        .and()
        .httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    
//    @Bean
//    public AuthenticationManager customAuthenticationManager(HttpSecurity http) throws Exception {
//        // Configure the AuthenticationManagerBuilder with your authentication providers
//        http.authenticationProvider(yourAuthenticationProvider); // Replace with your authentication provider
//        
//        // Return the AuthenticationManager
//        return http.getSharedObject(AuthenticationManager.class);
//    }
   


}
