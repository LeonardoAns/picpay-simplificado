package br.com.leonardo.picpay_simplificado.web.dto.customer.request

import br.com.leonardo.picpay_simplificado.core.enums.CustomerType
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.br.CPF

data class CustomerRequestDto(

    @NotEmpty(message = "First name is required")
    val firstName: String,

    @NotEmpty(message = "Last name is required")
    val lastName: String,

    @NotEmpty(message = "Document is required")
    @CPF(message = "Invalid cpf")
    val cpfCnpj: String,

    @Email(message = "Email is required")
    val email: String,

    @NotEmpty(message = "Password is required")
    val password: String,

    @NotEmpty(message = "Customer Type is required")
    val customerType: CustomerType
)
