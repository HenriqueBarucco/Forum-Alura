package com.henriquebarucco.forum.service

import com.henriquebarucco.forum.model.Curso
import com.henriquebarucco.forum.repository.CursoRepository
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class CursoService(private val repository: CursoRepository) {

    fun buscarPorId(id: Long): Curso {
        return repository.findById(id).get()
    }
}
