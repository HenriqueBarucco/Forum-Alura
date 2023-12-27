package com.henriquebarucco.forum.integration

import com.henriquebarucco.forum.dto.TopicoPorCategoriaDto
import com.henriquebarucco.forum.model.Topico
import com.henriquebarucco.forum.model.TopicoTest
import com.henriquebarucco.forum.repository.TopicoRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.AssertionsForClassTypes
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicoRepositoryTest {

    @Autowired
    private lateinit var topicoRepository: TopicoRepository

    private val topico = TopicoTest.build()

    companion object {
        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:8.0.23").apply {
            withDatabaseName("test_db")
            withUsername("henrique")
            withPassword("123456")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.username", mysqlContainer::getUsername)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
        }
    }

    @Test
    fun `deve gerar um relatorio`() {
        topicoRepository.save(topico)
        val relatorio = topicoRepository.relatorio()

        assertThat(relatorio).isNotNull
        assertThat(relatorio.first()).isExactlyInstanceOf(TopicoPorCategoriaDto::class.java)
    }

    @Test
    fun `deve listar topico pelo nome do curso`() {
        topicoRepository.save(topico)
        val topico = topicoRepository.findByCursoNome(topico.curso.nome, PageRequest.of(0, 5))

        assertThat(topico).isNotNull
    }
}