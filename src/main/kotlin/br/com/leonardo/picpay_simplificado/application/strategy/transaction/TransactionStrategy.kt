package br.com.leonardo.picpay_simplificado.application.strategy.transaction

import br.com.leonardo.picpay_simplificado.core.entities.Customer
import java.math.BigDecimal

interface TransactionStrategy {
    fun execute(transactionValue: BigDecimal, sender: Customer, receiver: Customer)
}