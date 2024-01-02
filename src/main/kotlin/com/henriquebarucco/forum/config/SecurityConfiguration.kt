package com.henriquebarucco.forum.config

import com.henriquebarucco.forum.security.JWTAuthenticationFilter
import com.henriquebarucco.forum.security.JWTLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val configuration: AuthenticationConfiguration,
    private val jwtUtil: JWTUtil
) {

    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain {

        http.invoke {
            csrf { disable() }
            authorizeRequests {
                authorize("/topicos", hasAuthority("LEITURA_ESCRITA"))
                authorize("/respostas", hasAuthority("LEITURA_ESCRITA"))
                authorize("/relatorios", hasAuthority("ADMIN"))
                authorize("/swagger-ui/**", permitAll)
                authorize("/v3/api-docs/**", permitAll)
                authorize(HttpMethod.POST, "/login", permitAll)
                authorize(anyRequest, authenticated)
            }
            http.addFilterBefore(JWTLoginFilter(authManager = configuration.authenticationManager, jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter::class.java)
            http.addFilterBefore(JWTAuthenticationFilter(jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter::class.java)
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
            headers { frameOptions { disable() } }
        }
        return http.build()
    }

    @Bean
    fun encoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }
}