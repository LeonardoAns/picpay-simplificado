package br.com.leonardo.picpay_simplificado.application.exception.model

import br.com.leonardo.picpay_simplificado.application.exception.base.AlreadyExistsException
import br.com.leonardo.picpay_simplificado.application.exception.base.NotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import java.awt.PageAttributes

@RestController
class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun notFoundException(notFoundException: NotFoundException, request: HttpServletRequest): ResponseEntity<ExceptionMessage> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(
                ExceptionMessage.from(
                    request = request,
                    status = HttpStatus.NOT_FOUND,
                    message = notFoundException.message ?: "Resource not found"
                )
            )
    }

    @ExceptionHandler(AlreadyExistsException::class)
    fun alreadyExistsException(alreadyExistsException: AlreadyExistsException, request: HttpServletRequest): ResponseEntity<ExceptionMessage> {
        return ResponseEntity.status(HttpStatus.CONFLICT).contentType(MediaType.APPLICATION_JSON).body(
                ExceptionMessage.from(
                    request = request,
                    status = HttpStatus.CONFLICT,
                    message = alreadyExistsException.message ?: "Resource already exists"
                )
            )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(exception: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ExceptionMessage> {
        val errors: Map<String, String> = exception.bindingResult.fieldErrors
            .associate { it.field to (it.defaultMessage ?: "Invalid value") }

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ExceptionMessage.from(
                request = request,
                status = HttpStatus.BAD_REQUEST,
                message = "Validation error",
                result = exception.bindingResult
            ))
    }
}