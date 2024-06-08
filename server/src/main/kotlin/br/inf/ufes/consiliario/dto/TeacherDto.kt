package br.inf.ufes.consiliario.dto

import br.inf.ufes.consiliario.entity.Teacher

data class TeacherDto(
    val id: String,
    val email: String,
    val username: String,
) {
    constructor(teacher: Teacher) : this(
        id = teacher.id.toString(),
        email = teacher.email,
        username = teacher.username
    )
}