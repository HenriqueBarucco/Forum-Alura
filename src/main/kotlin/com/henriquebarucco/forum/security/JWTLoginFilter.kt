package com.henriquebarucco.forum.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.henriquebarucco.forum.config.JWTUtil
import com.henriquebarucco.forum.model.Credentials
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JWTLoginFilter(
    private val authManager: AuthenticationManager?,
    private val jwtUtil: JWTUtil) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val (username, password) = ObjectMapper().readValue(request?.inputStream, Credentials::class.java)

        UsernamePasswordAuthenticationToken(username, password).let {
            return authManager?.authenticate(it)!!
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val user = (authResult?.principal as UserDetails)

        jwtUtil.generateToken(user.username, user.authorities).let {
            response?.addHeader("Authorization", "Bearer $it")
        }
    }
}
