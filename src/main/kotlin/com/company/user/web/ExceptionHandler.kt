package com.company.user.web

import com.company.user.exceptions.UserAppException
import com.company.user.web.api.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.RuntimeException

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [UserAppException::class])
    fun handle(e: UserAppException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = e.reason ?: e.message
        )
        return ResponseEntity(errorResponse, e.status)
    }

    @ExceptionHandler(value = [RuntimeException::class])
    fun handleRuntimeException(e: RuntimeException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = e.message ?: "Unknown error: ${e.cause}"
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

}