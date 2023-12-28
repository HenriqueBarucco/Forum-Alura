package com.henriquebarucco.forum.controller

import com.henriquebarucco.forum.config.JWTUtil
import com.henriquebarucco.forum.model.Role
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicoControllerTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    private lateinit var mockMvc: MockMvc

    private var token: String? = null

    companion object {
        private const val RESOURCE = "/topicos"
        private const val RESOURCE_ID = RESOURCE.plus("/%s")
    }

    @BeforeEach
    fun setup() {
        token = gerarToken()
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder?>(
                SecurityMockMvcConfigurers.springSecurity()
            ).build()
    }

    @Test
    fun `deve retornar codigo 400 quando chamar topicos sem token`() {
        mockMvc.get(RESOURCE).andExpect {
            status { is4xxClientError() }
        }
    }

    @Test
    fun `deve retornar codigo 200 quando chamar topicos com token`() {
        mockMvc.get(RESOURCE) {
            headers { token?.let { setBearerAuth(it) } }
        }.andExpect {
            status { is2xxSuccessful() }
        }
    }

    @Test
    fun `deve retornar codigo 200 quando chamar topico com id com token`() {
        mockMvc.get(RESOURCE_ID.format("1")) {
            headers { token?.let { setBearerAuth(it) } }
        }.andExpect {
            status { is2xxSuccessful() }
        }
    }

    private fun gerarToken(): String? {
        val authorities = mutableListOf(Role(1, "LEITURA_ESCRITA"))
        return jwtUtil.generateToken("ana@email.com", authorities)
    }
}