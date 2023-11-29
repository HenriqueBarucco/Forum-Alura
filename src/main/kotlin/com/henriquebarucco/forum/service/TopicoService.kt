package com.henriquebarucco.forum.service

import com.henriquebarucco.forum.dto.AtualizacaoTopicoForm
import com.henriquebarucco.forum.dto.NovoTopicoForm
import com.henriquebarucco.forum.dto.TopicoView
import com.henriquebarucco.forum.exception.NotFoundException
import com.henriquebarucco.forum.mapper.TopicoFormMapper
import com.henriquebarucco.forum.mapper.TopicoViewMapper
import com.henriquebarucco.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicoService(
        private var topicos: List<Topico> = ArrayList(),
        private val topicoViewMapper: TopicoViewMapper,
        private val topicoFormMapper: TopicoFormMapper,
        private val notFoundMessage: String = "Topico não encontrado"
    ) {

    fun listar(): List<TopicoView> {
        return topicos.stream().map { t -> topicoViewMapper.map(t) }.toList()
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.stream().filter { t -> t.id == id }.findFirst().orElseThrow{NotFoundException(notFoundMessage)}

        return topicoViewMapper.map(topico)
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        topico.id = topicos.size.toLong() + 1

        topicos = topicos.plus(topico)

        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = topicos.stream().filter { t -> t.id == form.id }.findFirst().orElseThrow{NotFoundException(notFoundMessage)}

        val topicoAtualizado = Topico(
            id = topico.id,
            titulo = form.titulo,
            mensagem = form.mensagem,
            curso = topico.curso,
            autor = topico.autor,
            respostas = topico.respostas,
            status = topico.status,
            dataCriacao = topico.dataCriacao
        )
        topicos = topicos.minus(topico).plus(topicoAtualizado)

        return topicoViewMapper.map(topicoAtualizado)
    }

    fun deletar(id: Long) {
        val topico = topicos.stream().filter { t -> t.id == id }.findFirst().orElseThrow{NotFoundException(notFoundMessage)}

        topicos = topicos.minus(topico)
    }
}