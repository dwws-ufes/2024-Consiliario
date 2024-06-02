package br.inf.ufes.consiliario.dto

data class TeacherRecoverPasswordDto(
    val email: String,
    val oldPassword: String,
    val newPassword: String
)