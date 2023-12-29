package com.henriquebarucco.forum.repository

import com.henriquebarucco.forum.model.Resposta
import org.springframework.data.jpa.repository.JpaRepository

interface RespostaRepository: JpaRepository<Resposta, Long> {
}