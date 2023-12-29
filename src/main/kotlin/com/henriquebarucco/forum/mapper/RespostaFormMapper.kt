package com.henriquebarucco.forum.mapper

import com.henriquebarucco.forum.dto.NovaRespostaForm
import com.henriquebarucco.forum.model.Resposta
import com.henriquebarucco.forum.service.TopicoService
import com.henriquebarucco.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class RespostaFormMapper(
    private val usuarioService: UsuarioService,
    private val topicoService: TopicoService,
): Mapper<NovaRespostaForm, Resposta> {

    override fun map(t: NovaRespostaForm): Resposta {
        return Resposta(
            mensagem = t.mensagem,
            autor = usuarioService.buscarPorId(t.idAutor),
            topico = topicoService.buscarTopicoPorId(t.idTopico),
            solucao = false
        )
    }
}
