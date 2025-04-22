package br.com.leonardo.picpay_simplificado.core.usecases.transaction

import br.com.leonardo.picpay_simplificado.web.dto.transaction.request.TransactionRequestDto

interface MakeTransactionUseCase {
    fun execute(transactionRequest: TransactionRequestDto)
}