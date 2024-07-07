package br.inf.ufes.consiliario.dto

import br.inf.ufes.consiliario.entity.Meeting
import java.time.Instant
import java.util.*

class MeetingRegisterDto(
    val datetime: Instant,
    val teacher: UUID,
    val student: UUID,
    val location: String
) {
    fun toEntity() = Meeting(
        datetime = datetime,
        teacher = teacher,
        student = student,
        location = location
    )
}