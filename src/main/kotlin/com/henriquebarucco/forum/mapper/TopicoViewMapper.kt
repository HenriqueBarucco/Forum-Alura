package com.henriquebarucco.forum.mapper

import com.henriquebarucco.forum.dto.TopicoView
import com.henriquebarucco.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper: Mapper<Topico, TopicoView> {

    override fun map(t: Topico): TopicoView {
        return TopicoView(
            id = t.id,
            titulo = t.titulo,
            mensagem = t.mensagem,
            status = t.status,
            dataCriacao = t.dataCriacao,
            dataAlteracao = t.dataAlteracao
        )
    }
}