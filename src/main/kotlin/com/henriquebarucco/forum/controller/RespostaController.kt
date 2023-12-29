package com.henriquebarucco.forum.controller

import com.henriquebarucco.forum.dto.NovaRespostaForm
import com.henriquebarucco.forum.model.Resposta
import com.henriquebarucco.forum.service.RespostaService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/respostas")
class RespostaController(
    private val respostaService: RespostaService
) {

    @PostMapping
    @Transactional
    fun salvar(@RequestBody @Valid form: NovaRespostaForm) = respostaService.salvar(form)
}