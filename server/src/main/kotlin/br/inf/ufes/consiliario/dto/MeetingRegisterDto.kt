package br.inf.ufes.consiliario.dto

import br.inf.ufes.consiliario.entity.Meeting
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime
import java.util.*

class MeetingRegisterDto(
    val dateTime: DateTime,
    val teacher: UUID,
    val student: UUID,
    val location: String
) {
    fun toEntity() = Meeting(
        dateTime = dateTime,
        teacher = teacher,
        student = student,
        location = location
    )
}