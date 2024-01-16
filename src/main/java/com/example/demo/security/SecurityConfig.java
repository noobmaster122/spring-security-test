package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.jwt.JwtAuthEntryPoint;
import com.example.demo.jwt.JwtAuthFilter;
import com.example.demo.jwt.JwtGenerator;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtAuthEntryPoint authEntryPoint;
    private CustomUserDetailsService userDetailsService;
    private CustomUserDetailsService customUserDetailsService;
    private JwtAuthFilter jwtAuthFilter;
    
    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService,
    						JwtAuthEntryPoint authEntryPoint,
    						CustomUserDetailsService customUserDetailsService,
    						JwtAuthFilter jwtAuthFilter) {
        this.userDetailsService = userDetailsService;
        this.authEntryPoint = authEntryPoint;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .exceptionHandling()
        .authenticationEntryPoint(authEntryPoint)
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterAfter(new JwtAuthFilter(new JwtGenerator(), this.customUserDetailsService), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers("/api/public/**").permitAll() // Allow access to public endpoints
        .anyRequest().authenticated() // Requires authentication for other endpoints
        .and()
        .httpBasic();

        //http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public JwtAuthFilter jwtAuthenticationFilter() {
    	return this.jwtAuthFilter;
    }
   


}
