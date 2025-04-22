package br.com.leonardo.picpay_simplificado.application.strategy.transaction.impl

import br.com.leonardo.picpay_simplificado.application.strategy.transaction.TransactionStrategy
import br.com.leonardo.picpay_simplificado.core.entities.Customer
import br.com.leonardo.picpay_simplificado.application.exception.base.BadRequestException
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class SufficientBalanceValidation: TransactionStrategy {
    override fun execute(transactionValue: BigDecimal, sender: Customer, receiver: Customer) {
        if(sender.balance < transactionValue){
            throw BadRequestException("Insufficient balance")
        }
    }
}