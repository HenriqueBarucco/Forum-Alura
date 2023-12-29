package com.henriquebarucco.forum.service

import com.henriquebarucco.forum.dto.NovaRespostaForm
import com.henriquebarucco.forum.mapper.RespostaFormMapper
import com.henriquebarucco.forum.repository.RespostaRepository
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val respostaRepository: RespostaRepository,
    private val respostaFormMapper: RespostaFormMapper,
) {

    fun salvar(form: NovaRespostaForm) {
        val resposta = respostaFormMapper.map(form)
        respostaRepository.save(resposta)
    }
}