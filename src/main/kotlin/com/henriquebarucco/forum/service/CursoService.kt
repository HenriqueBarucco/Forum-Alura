package com.henriquebarucco.forum.service

import com.henriquebarucco.forum.model.Curso
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class CursoService(var cusos: List<Curso>) {

    init {
        val curso = Curso(
            id = 1,
            nome = "Kotlin",
            categoria = "Programação"
        )

        cusos = Arrays.asList(curso)
    }

    fun buscarPorId(id: Long): Curso {
        return cusos.stream().filter { c -> c.id == id }.findFirst().get()
    }
}
