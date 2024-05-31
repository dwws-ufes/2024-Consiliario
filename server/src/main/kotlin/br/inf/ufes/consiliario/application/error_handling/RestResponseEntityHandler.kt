package br.inf.ufes.consiliario.application.error_handling

import br.inf.ufes.consiliario.dto.ErrorResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler

/**
 * Class responsible for managing exceptions globally. It contains customized error handling logic for each type of used
 * exception.
 */
@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    /**
     * Catches: [BadCredentialsException]
     * Returns: [HttpStatus.BAD_REQUEST]
     *
     * Handles credential errors, including mistyping username and password.
     */
    @ExceptionHandler(BadCredentialsException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadRequestException(ex: BadCredentialsException, request: WebRequest): ResponseEntity<ErrorResponseDto> {
        val response = ErrorResponseDto(
            HttpStatus.BAD_REQUEST.value(),
            "Bad credentials",
            "Did you type the correct username and password?"
        )

        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }
}