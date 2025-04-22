package br.com.leonardo.picpay_simplificado.infrastructure.persistence

import br.com.leonardo.picpay_simplificado.core.entities.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository: JpaRepository<Transaction, Long> {
}