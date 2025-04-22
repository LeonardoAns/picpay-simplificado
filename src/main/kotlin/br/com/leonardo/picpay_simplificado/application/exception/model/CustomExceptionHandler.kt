package br.com.leonardo.picpay_simplificado.application.exception.model

import br.com.leonardo.picpay_simplificado.application.exception.base.AlreadyExistsException
import br.com.leonardo.picpay_simplificado.application.exception.base.BadRequestException
import br.com.leonardo.picpay_simplificado.application.exception.base.BusinessException
import br.com.leonardo.picpay_simplificado.application.exception.base.NotFoundException
import jakarta.servlet.http.HttpServletRequest
import lombok.extern.java.Log
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import java.awt.PageAttributes

@ControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun notFoundException(notFoundException: NotFoundException, request: HttpServletRequest): ResponseEntity<ExceptionMessage> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ExceptionMessage(request, HttpStatus.NOT_FOUND, notFoundException.message ?: "Resource not found"))
    }

    @ExceptionHandler(AlreadyExistsException::class)
    fun alreadyExistsException(alreadyExistsException: AlreadyExistsException, request: HttpServletRequest): ResponseEntity<ExceptionMessage> {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ExceptionMessage(request, HttpStatus.CONFLICT, alreadyExistsException.message ?: "Conflict"))
    }

    @ExceptionHandler(BadRequestException::class)
    fun badRequestException(badRequestException: BadRequestException, request: HttpServletRequest): ResponseEntity<ExceptionMessage> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ExceptionMessage(request, HttpStatus.BAD_REQUEST, badRequestException.message ?: "Conflict"))
    }

    @ExceptionHandler(BusinessException::class)
    fun businessException(businessException: BusinessException, request: HttpServletRequest): ResponseEntity<ExceptionMessage> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ExceptionMessage(request, HttpStatus.BAD_REQUEST, businessException.message ?: "Business error"))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ExceptionMessage> {

        val exceptionMessage = ExceptionMessage(request, HttpStatus.BAD_REQUEST, "Validation error", ex.bindingResult)

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(exceptionMessage)
    }

}