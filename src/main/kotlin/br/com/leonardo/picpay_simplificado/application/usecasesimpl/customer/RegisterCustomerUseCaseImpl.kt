package br.com.leonardo.picpay_simplificado.application.usecasesimpl.customer

import br.com.leonardo.picpay_simplificado.core.entities.Customer
import br.com.leonardo.picpay_simplificado.core.usecases.customer.RegisterCustomerUseCase
import br.com.leonardo.picpay_simplificado.infrastructure.persistence.CustomerRepository
import br.com.leonardo.picpay_simplificado.web.dto.customer.request.CustomerRequestDto
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class RegisterCustomerUseCaseImpl(
    private val customerRepository: CustomerRepository,
    private val modelMapper: ModelMapper
): RegisterCustomerUseCase {

    override fun execute(registerCustomerRequest: CustomerRequestDto) {
        val customer: Customer = modelMapper.map(registerCustomerRequest, Customer::class.java)
        val existsByEmail: Boolean = customerRepository.existsByEmail(customer.email)
        if(existsByEmail) {
            throw RuntimeException("Email already registered")
            TODO("Implements custom exception")
        }
        customerRepository.save(customer)
    }
}