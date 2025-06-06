package br.com.leonardo.picpay_simplificado.application.usecasesimpl.customer

import br.com.leonardo.picpay_simplificado.application.exception.base.AlreadyExistsException
import br.com.leonardo.picpay_simplificado.core.entities.Customer
import br.com.leonardo.picpay_simplificado.core.usecases.customer.RegisterCustomerUseCase
import br.com.leonardo.picpay_simplificado.infrastructure.persistence.CustomerRepository
import br.com.leonardo.picpay_simplificado.web.dto.customer.request.CustomerRequestDto
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class RegisterCustomerUseCaseImpl(
    private val customerRepository: CustomerRepository,
): RegisterCustomerUseCase {

    override fun execute(registerCustomerRequest: CustomerRequestDto) {
        val customer: Customer = Customer(
            email = registerCustomerRequest.email,
            firstName = registerCustomerRequest.firstName,
            lastName = registerCustomerRequest.lastName,
            cpfCnpj = registerCustomerRequest.cpfCnpj,
            password = registerCustomerRequest.password,
            customerType = registerCustomerRequest.customerType
        )

        val existsByEmail: Boolean = customerRepository.existsByEmail(customer.email)
        if(existsByEmail) {
            throw AlreadyExistsException("Email already registered")
        }
        customerRepository.save(customer)
    }
}