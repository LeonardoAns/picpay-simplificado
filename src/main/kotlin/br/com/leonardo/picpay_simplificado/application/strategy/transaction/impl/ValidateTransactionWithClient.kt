package br.com.leonardo.picpay_simplificado.application.strategy.transaction.impl

import br.com.leonardo.picpay_simplificado.application.strategy.transaction.TransactionStrategy
import br.com.leonardo.picpay_simplificado.core.entities.Customer
import br.com.leonardo.picpay_simplificado.infrastructure.client.ValidateTransactionClient
import br.com.leonardo.picpay_simplificado.web.dto.transaction.response.ValidationTransactionResponse
import java.math.BigDecimal

class ValidateTransactionWithClient(
    private val validateTransactionClient: ValidateTransactionClient,
): TransactionStrategy {
    override fun execute(transactionValue: BigDecimal, sender: Customer, receiver: Customer) {
        val response: ValidationTransactionResponse = validateTransactionClient.execute()
        if(response.status != "success"){
            throw RuntimeException("Transaction validation failed")
        }
    }
}
