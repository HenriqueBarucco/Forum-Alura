package com.henriquebarucco.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class NovaRespostaForm(
    @field:NotEmpty(message = "Mensagem  nao pode ser em branco") val mensagem: String,
    @field:NotNull val idAutor: Long,
    @field:NotNull val idTopico: Long
) {
}