package com.henriquebarucco.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val javaMailSender: JavaMailSender
) {

    fun notificar(emailAutor: String) {
        val message = SimpleMailMessage()

        message.subject = "Nova resposta"
        message.text = "Você tem uma nova resposta em seu tópico!"
        message.setTo(emailAutor)

        javaMailSender.send(message)
    }
}