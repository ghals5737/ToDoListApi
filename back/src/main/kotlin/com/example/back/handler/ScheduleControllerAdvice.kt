package com.example.back.handler

import com.example.back.controller.ScheduleController
import com.example.back.exception.Error
import com.example.back.exception.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

@ControllerAdvice(basePackageClasses = [ScheduleController::class])
class ScheduleControllerAdvice {

    @ExceptionHandler(IllegalArgumentException::class)
    fun exceptionHandler(e: IllegalArgumentException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errors= mutableListOf<Error>()
        errors.add(Error(e.message,e.toString()))

        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "잘못된 매개변수 타입입니다."
            this.path = request.requestURI
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }
        return ResponseEntity.badRequest().body(errorResponse)
    }
}