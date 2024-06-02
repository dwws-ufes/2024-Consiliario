package br.inf.ufes.consiliario.dto

data class ErrorResponseDto(val status: Int, val message: String, val details: String)