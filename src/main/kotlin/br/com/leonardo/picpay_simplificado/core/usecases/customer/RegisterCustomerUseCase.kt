package br.com.leonardo.picpay_simplificado.core.usecases.customer

import br.com.leonardo.picpay_simplificado.web.dto.customer.request.CustomerRequestDto

interface RegisterCustomerUseCase {
    fun execute(registerCustomerRequest: CustomerRequestDto)
}