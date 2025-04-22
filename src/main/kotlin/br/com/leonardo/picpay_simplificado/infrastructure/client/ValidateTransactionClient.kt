package br.com.leonardo.picpay_simplificado.infrastructure.client

import br.com.leonardo.picpay_simplificado.web.dto.transaction.response.ValidationTransactionResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(path = "https://util.devi.tools/api/v2")
interface ValidateTransactionClient {

    @GetMapping("/authorize")
    fun execute(): ValidationTransactionResponse
}