package br.com.leonardo.picpay_simplificado.web.dto.transaction.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class TransactionRequestDto(

    @JsonProperty("payer")
    val senderId: Long,

    @JsonProperty(value = "payee")
    val receiverId: Long,
    val amount: BigDecimal,
)
