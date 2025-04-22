package br.com.leonardo.picpay_simplificado.core.entities

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
data class Transaction(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "sender_id")
    val sender: Customer,

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    val receiver: Customer,

    val ammount: BigDecimal,
)
