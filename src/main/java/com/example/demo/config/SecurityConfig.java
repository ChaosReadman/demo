package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http,
                        HandlerMappingIntrospector handlerMappingIntrospector)
                        throws Exception {
                MvcRequestMatcher mvcRequestMatcher = new MvcRequestMatcher.Builder(handlerMappingIntrospector)
                                .servletPath("/h2-console")
                                .pattern("/**");
                return http
                                .csrf(csrf->csrf
                                .ignoringRequestMatchers(mvcRequestMatcher))
                                .authorizeHttpRequests(requests-> requests
                                                .requestMatchers(mvcRequestMatcher)
                                                .permitAll()
                                                .anyRequest()
                                                .authenticated())
                                .securityMatcher("/members/**")
                                .headers((headers) -> headers.frameOptions((frame) -> frame.sameOrigin()))
                                .formLogin((form) -> form
                                                .loginPage("/members/login")
                                                .permitAll())
                                .logout((logout) -> logout.permitAll())
                                .build();
        }

        @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}