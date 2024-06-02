package br.inf.ufes.consiliario.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

@Table("Student")
data class Student(
    @Id val id: UUID? = null,
    val email: String,
    val fullName: String,
    val advisor: UUID
)