package net.luizkowalski.accountsservice.presentation.controllers.controllerAdvice

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class ControllerAdvices {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<Any> {
        val fieldErrors = ex.bindingResult.fieldErrors
        var errors = emptyList<Error>()
        fieldErrors.forEach { errors = errors.plus(ControllerAdvices.Error(it.code, it.defaultMessage, it.field)) }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    data class Error(var code: String, var message: String, var field: String = "")
}