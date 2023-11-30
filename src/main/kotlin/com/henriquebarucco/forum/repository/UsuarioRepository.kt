package com.henriquebarucco.forum.repository

import com.henriquebarucco.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {
}