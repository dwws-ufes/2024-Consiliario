package br.inf.ufes.consiliario.controller

import br.inf.ufes.consiliario.application.MeetingApplication
import br.inf.ufes.consiliario.dto.MeetingRegisterDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/meeting")
class MeetingController(
    private val meetingApplication: MeetingApplication
) {
    @GetMapping
    suspend fun getMeeting(
        @RequestParam meetingId: UUID
    ) = meetingApplication.getMeeting(meetingId)

    @PostMapping
    suspend fun registerMeeting(
        @RequestBody meetingRegisterDto: MeetingRegisterDto
    ) = meetingApplication.registerMeeting(meetingRegisterDto)

    @GetMapping("/from-teacher")
    suspend fun getAllFromTeacher(
        @RequestParam teacherId: UUID
    ) = meetingApplication.getAllFromTeacher(teacherId)
}