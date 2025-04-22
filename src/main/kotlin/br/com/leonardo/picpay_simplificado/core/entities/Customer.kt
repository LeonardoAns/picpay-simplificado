package br.com.leonardo.picpay_simplificado.core.entities

import br.com.leonardo.picpay_simplificado.core.enums.CustomerType
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
data class Customer(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val firstName: String,
    val lastName: String,

    @Column(unique = true)
    val cpfCnpj: String,

    @Column(unique = true)
    val email: String,
    val password: String,
    var balance: BigDecimal = 100.toBigDecimal(),

    @Enumerated(EnumType.STRING)
    val customerType: CustomerType
)
