package br.com.leonardo.picpay_simplificado.application.strategy.transaction.impl

import br.com.leonardo.picpay_simplificado.application.exception.base.BusinessException
import br.com.leonardo.picpay_simplificado.application.strategy.transaction.TransactionStrategy
import br.com.leonardo.picpay_simplificado.core.entities.Customer
import br.com.leonardo.picpay_simplificado.core.enums.CustomerType
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class CommonSenderValidation: TransactionStrategy {
    override fun execute(transactionValue: BigDecimal, sender: Customer, receiver: Customer) {
        if(sender.customerType != CustomerType.COMMON){
            throw BusinessException("Merchants cannot make transactions")
        }
    }
}