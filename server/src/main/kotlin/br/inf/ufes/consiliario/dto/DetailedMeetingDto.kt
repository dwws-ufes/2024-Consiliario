package br.inf.ufes.consiliario.dto

import br.inf.ufes.consiliario.entity.Meeting

class DetailedMeetingDto(
    val id: String,
    val datetime: String,
    val teacher: String,
    val student: String,
    val location: String,
    var label: String?,
    var description: String?
) {
    constructor(entity: Meeting) : this(
        id = entity.id.toString(),
        datetime = entity.datetime.toString(),
        teacher = entity.teacher.toString(),
        student = entity.student.toString(),
        location = entity.location,
        label = null,
        description = null
    )
}