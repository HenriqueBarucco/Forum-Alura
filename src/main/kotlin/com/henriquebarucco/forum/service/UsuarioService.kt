package com.henriquebarucco.forum.service

import com.henriquebarucco.forum.model.Usuario
import com.henriquebarucco.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class UsuarioService(private val repository: UsuarioRepository) {

    fun buscarPorId(id: Long): Usuario {
        return repository.findById(id).get()
    }
}
