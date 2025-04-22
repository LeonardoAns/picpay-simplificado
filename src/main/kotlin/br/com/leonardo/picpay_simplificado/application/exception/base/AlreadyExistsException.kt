package br.com.leonardo.picpay_simplificado.application.exception.base

import br.com.leonardo.picpay_simplificado.application.exception.model.ExceptionMessage
import org.springframework.http.HttpStatus

class AlreadyExistsException(message: String) : RuntimeException(message) {
}