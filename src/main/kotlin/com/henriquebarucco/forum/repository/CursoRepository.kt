package com.henriquebarucco.forum.repository

import com.henriquebarucco.forum.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

interface CursoRepository: JpaRepository<Curso, Long> {
}