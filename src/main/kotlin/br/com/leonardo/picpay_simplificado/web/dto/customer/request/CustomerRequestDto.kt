package br.com.leonardo.picpay_simplificado.web.dto.customer.request

import br.com.leonardo.picpay_simplificado.core.enums.CustomerType
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF

data class CustomerRequestDto(

    @field:NotEmpty(message = "First name is required")
    val firstName: String,

    @field:NotEmpty(message = "Last name is required")
    val lastName: String,

    @field:NotEmpty(message = "Document is required")
    @field:CPF(message = "Invalid cpf")
    val cpfCnpj: String,

    @field:Email(message = "Invalid email")
    @field:NotEmpty(message = "Email is required")
    val email: String,

    @field:NotEmpty(message = "Password is required")
    val password: String,

    @field:NotNull(message = "Customer Type is required")
    val customerType: CustomerType
)
