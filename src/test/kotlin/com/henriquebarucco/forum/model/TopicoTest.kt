package com.henriquebarucco.forum.model

object TopicoTest {
    fun build() = Topico(
        id = 1L,
        titulo = "Titulo",
        mensagem = "Mensagem",
        curso = CursoTest.build(),
        autor = UsuarioTest.build(),
    )
}