package com.henriquebarucco.forum.dto

import com.henriquebarucco.forum.model.StatusTopico
import java.time.LocalDate
import java.time.LocalDateTime

data class TopicoView(
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime,
    val dataAlteracao: LocalDate?,
) {

}
