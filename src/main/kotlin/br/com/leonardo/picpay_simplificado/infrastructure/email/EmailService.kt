package br.com.leonardo.picpay_simplificado.infrastructure.email

import br.com.leonardo.picpay_simplificado.core.entities.Customer
import java.math.BigDecimal

interface EmailService {
    fun send(to: Customer, amount: BigDecimal)
}