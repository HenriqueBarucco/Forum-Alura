package com.henriquebarucco.forum.config

import com.henriquebarucco.forum.service.UsuarioService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JWTUtil(
    private val usuarioService: UsuarioService
) {

    private val expiration : Long = 1 * 60 * 1000

    @Value("\${jwt.secret}")
    private lateinit var secret : String

    fun generateToken(username: String?, authorities: MutableCollection<out GrantedAuthority>): String? {
        return Jwts.builder()
            .setSubject(username)
            .claim("authorities", authorities)
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .compact()
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).build().parseSignedClaims(jwt)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAuthentication(jwt: String?) : Authentication {
        val username = Jwts.parser().setSigningKey(secret.toByteArray()).build().parseSignedClaims(jwt).payload.subject
        val user = usuarioService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }
}