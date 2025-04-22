package br.com.leonardo.picpay_simplificado.infrastructure.persistence

import br.com.leonardo.picpay_simplificado.core.entities.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.email = :email")
    fun existsByEmail(@Param("email")email: String): Boolean

}