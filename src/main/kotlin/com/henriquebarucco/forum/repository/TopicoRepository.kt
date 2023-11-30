package com.henriquebarucco.forum.repository

import com.henriquebarucco.forum.model.Topico
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository: JpaRepository<Topico, Long> {
}