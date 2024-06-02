package br.inf.ufes.consiliario.dto

import br.inf.ufes.consiliario.entity.Student
import java.util.*

class StudentRegisterDto(
    val email: String,
    val fullName: String,
    val advisor: UUID
) {
    fun toEntity() = Student(
        fullName = fullName,
        email = email,
        advisor = advisor
    )
}