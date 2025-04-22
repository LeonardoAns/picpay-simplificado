package br.com.leonardo.picpay_simplificado.application.strategy.transaction

import br.com.leonardo.picpay_simplificado.core.entities.Customer
import br.com.leonardo.picpay_simplificado.web.dto.customer.request.CustomerRequestDto
import br.com.leonardo.picpay_simplificado.web.dto.transaction.request.TransactionRequestDto
import java.math.BigDecimal

interface TransactionStrategy {
    fun execute(transactionValue: BigDecimal, sender: Customer, receiver: Customer)
}