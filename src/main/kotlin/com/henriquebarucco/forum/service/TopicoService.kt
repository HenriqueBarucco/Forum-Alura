package com.henriquebarucco.forum.service

import com.henriquebarucco.forum.model.Curso
import com.henriquebarucco.forum.model.Topico
import com.henriquebarucco.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicoService(private var topicos: List<Topico>) {

    init {
        val topico = Topico(
            id = 1,
            titulo = "Duvida Kotlin",
            mensagem = "Variaveis",
            curso = Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programacao"
            ),
            autor = Usuario(
                id = 1,
                nome = "Henrique",
                email = "henrique@email.com"
            ),
        )

        val topico2 = Topico(
            id = 2,
            titulo = "Duvida Kotlin",
            mensagem = "Variaveis",
            curso = Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programacao"
            ),
            autor = Usuario(
                id = 1,
                nome = "Henrique",
                email = "henrique@email.com"
            ),
        )

        val topico3 = Topico(
            id = 3,
            titulo = "Duvida Kotlin",
            mensagem = "Variaveis",
            curso = Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programacao"
            ),
            autor = Usuario(
                id = 1,
                nome = "Henrique",
                email = "henrique@email.com"
            ),
        )

        topicos = Arrays.asList(topico, topico2, topico3)
    }

    fun listar(): List<Topico> {
        return topicos
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.stream().filter { t -> t.id == id }.findFirst().get()
    }
}