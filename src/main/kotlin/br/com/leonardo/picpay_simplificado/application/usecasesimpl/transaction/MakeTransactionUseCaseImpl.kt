package br.com.leonardo.picpay_simplificado.application.usecasesimpl.transaction

import br.com.leonardo.picpay_simplificado.application.exception.base.NotFoundException
import br.com.leonardo.picpay_simplificado.application.strategy.transaction.TransactionStrategy
import br.com.leonardo.picpay_simplificado.core.entities.Transaction
import br.com.leonardo.picpay_simplificado.core.usecases.transaction.MakeTransactionUseCase
import br.com.leonardo.picpay_simplificado.infrastructure.client.ValidateTransactionClient
import br.com.leonardo.picpay_simplificado.infrastructure.email.EmailService
import br.com.leonardo.picpay_simplificado.infrastructure.persistence.CustomerRepository
import br.com.leonardo.picpay_simplificado.infrastructure.persistence.TransactionRepository
import br.com.leonardo.picpay_simplificado.web.dto.transaction.request.TransactionRequestDto
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Private

@Service
@Transactional
class MakeTransactionUseCaseImpl(
    private val customerRepository: CustomerRepository,
    private val transactionStrategy: List<TransactionStrategy>,
    private val transactionRepository: TransactionRepository,
    private val emailService: EmailService,
): MakeTransactionUseCase {
    override fun execute(transactionRequest: TransactionRequestDto) {
        val senderCustomer = customerRepository.findById(transactionRequest.senderId)
            .orElseThrow { NotFoundException("Sender not found") }

        val receiverCustomer = customerRepository.findById(transactionRequest.receiverId)
            .orElseThrow { NotFoundException("Receiver not found") }


        transactionStrategy.forEach { strategy ->
            strategy.execute(transactionRequest.amount, senderCustomer, receiverCustomer)
        }

        emailService.send(receiverCustomer, transactionRequest.amount)

        val transaction: Transaction = Transaction(
            sender = senderCustomer,
            receiver = receiverCustomer,
            amount = transactionRequest.amount
        )
        senderCustomer.balance -= transactionRequest.amount
        receiverCustomer.balance += transactionRequest.amount
        customerRepository.save(senderCustomer)
        customerRepository.save(receiverCustomer)
        transactionRepository.save(transaction)
    }
}
