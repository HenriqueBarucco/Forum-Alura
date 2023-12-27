package com.henriquebarucco.forum.model

object UsuarioTest {
    fun build() = Usuario(
        id = 1L,
        nome = "Nome",
        email = "Email",
        password = "Senha"
    )
}