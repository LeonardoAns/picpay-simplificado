package br.com.leonardo.picpay_simplificado.web.controller.customer

import br.com.leonardo.picpay_simplificado.core.usecases.customer.RegisterCustomerUseCase
import br.com.leonardo.picpay_simplificado.web.dto.customer.request.CustomerRequestDto
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/customers")
class CustomerController(
    private val registerCustomerUseCase: RegisterCustomerUseCase
) {

    @PostMapping("/register")
    fun registerCustomer(@RequestBody @Valid customerRequestDto: CustomerRequestDto): ResponseEntity<Unit> {
        registerCustomerUseCase.execute(customerRequestDto)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}