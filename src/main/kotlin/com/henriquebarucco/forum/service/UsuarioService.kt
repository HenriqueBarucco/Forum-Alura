package com.henriquebarucco.forum.service

import com.henriquebarucco.forum.model.Usuario
import com.henriquebarucco.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class UsuarioService(private val repository: UsuarioRepository) : UserDetailsService {

    fun buscarPorId(id: Long): Usuario {
        return repository.findById(id).get()
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario = repository.findByEmail(username) ?: throw RuntimeException("Usuário não encontrado")
        return UserDetail(usuario)
    }
}
