package br.com.leonardo.picpay_simplificado.application.exception.model

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult

data class ExceptionMessage(
    var path: String? = null,
    var method: String? = null,
    var statusCode: Int? = null,
    var statusText: String? = null,
    var message: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var errors: Map<String, String>? = null
) {

    constructor(request: HttpServletRequest, status: HttpStatus, message: String) : this() {
        this.path = request.requestURI
        this.method = request.method
        this.statusCode = status.value()
        this.statusText = status.reasonPhrase
        this.message = message
    }

    constructor(request: HttpServletRequest, status: HttpStatus, message: String, result: BindingResult) : this() {
        this.path = request.requestURI
        this.method = request.method
        this.statusCode = status.value()
        this.statusText = status.reasonPhrase
        this.message = message
        this.errors = addErrors(result)
    }

    private fun addErrors(result: BindingResult): Map<String, String> {
        return result.fieldErrors.associate { it.field to (it.defaultMessage ?: "Invalid value") }
    }
}