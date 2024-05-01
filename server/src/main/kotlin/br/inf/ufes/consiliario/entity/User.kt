package br.inf.ufes.consiliario.entity

import org.springframework.data.annotation.Id
import java.util.UUID

data class User(
    @Id val id: UUID,
    val name: String,
    val email: String,
    val password: String
)