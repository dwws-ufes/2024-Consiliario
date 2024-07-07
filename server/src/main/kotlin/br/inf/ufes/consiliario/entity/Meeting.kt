package br.inf.ufes.consiliario.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.UUID

@Table("Meeting")
data class Meeting(
    @Id val id: UUID? = null,
    val datetime: Instant,
    val teacher: UUID,
    val student: UUID,
    val location: String
)