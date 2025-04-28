package br.com.leonardo.picpay_simplificado.infrastructure.email.impl

import br.com.leonardo.picpay_simplificado.core.entities.Customer
import br.com.leonardo.picpay_simplificado.infrastructure.email.EmailService
import lombok.extern.java.Log
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import org.thymeleaf.context.Contexts
import java.math.BigDecimal
import java.nio.charset.StandardCharsets
import java.time.LocalDate

@Service
class TransactionApprovedNotifier(
    private val javaMailSender: JavaMailSender,
    private val templateEngine: TemplateEngine
): EmailService {

    override fun send(to: Customer, amount: BigDecimal) {
        val message = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(
            message,
            MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
            StandardCharsets.UTF_8.name()
        )

        val context: Context = Context()
        context.setVariable("userName", to.firstName)
        context.setVariable("transactionDate", LocalDate.now())
        context.setVariable("amount", amount)

        val content: String = templateEngine.process("transaction_received_email", context)
        helper.setTo(to.email)
        println("Sending email to ${to.email}")
        helper.setText(content,true);
        helper.setSubject("Transação recebida")
        helper.setFrom("leonardo.anschau@icloud.com")

        javaMailSender.send(message)

    }
}