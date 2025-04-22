package br.com.leonardo.picpay_simplificado.application.exception.model

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult

data class ExceptionMessage(
    val path: String,
    val method: String,
    val statusCode: Int,
    val statusText: String,
    val message: String,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val errors: Map<String, String>? = null
) {
    companion object {
        fun from(request: HttpServletRequest, status: HttpStatus, message: String): ExceptionMessage {
            return ExceptionMessage(
                path = request.requestURI,
                method = request.method,
                statusCode = status.value(),
                statusText = status.reasonPhrase,
                message = message
            )
        }

        fun from(request: HttpServletRequest, status: HttpStatus, message: String, result: BindingResult): ExceptionMessage {
            val fieldErrors = result.fieldErrors.associate { it.field to (it.defaultMessage ?: "Erro desconhecido") }
            return ExceptionMessage(
                path = request.requestURI,
                method = request.method,
                statusCode = status.value(),
                statusText = status.reasonPhrase,
                message = message,
                errors = if (fieldErrors.isNotEmpty()) fieldErrors else null
            )
        }
    }
}
