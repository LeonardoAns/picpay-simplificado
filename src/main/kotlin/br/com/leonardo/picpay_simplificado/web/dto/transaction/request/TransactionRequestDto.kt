package br.com.leonardo.picpay_simplificado.web.dto.transaction.request

import java.math.BigDecimal

data class TransactionRequestDto(
    val senderId: Long,
    val receiverId: Long,
    val amount: BigDecimal,
)
