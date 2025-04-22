package br.com.leonardo.picpay_simplificado.web.controller.transaction

import br.com.leonardo.picpay_simplificado.core.usecases.transaction.MakeTransactionUseCase
import br.com.leonardo.picpay_simplificado.web.dto.transaction.request.TransactionRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/transactions")
class TransactionController(
    private val makeTransactionUseCase: MakeTransactionUseCase
) {

    @PostMapping("/transfer")
    fun makeTransaction(@RequestBody transactionRequestDto: TransactionRequestDto): ResponseEntity<Unit> {
        makeTransactionUseCase.execute(transactionRequestDto)
        return ResponseEntity.ok().build()
    }
}