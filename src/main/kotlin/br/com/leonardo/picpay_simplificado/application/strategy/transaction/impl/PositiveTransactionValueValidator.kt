package br.com.leonardo.picpay_simplificado.application.strategy.transaction.impl

import br.com.leonardo.picpay_simplificado.application.exception.base.BadRequestException
import br.com.leonardo.picpay_simplificado.application.strategy.transaction.TransactionStrategy
import br.com.leonardo.picpay_simplificado.core.entities.Customer
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class PositiveTransactionValueValidator: TransactionStrategy {

    override fun execute(transactionValue: BigDecimal, sender: Customer, receiver: Customer) {
        if(transactionValue < BigDecimal.ZERO){
            throw BadRequestException("Transaction value must be greater than zero")
        }
    }
}