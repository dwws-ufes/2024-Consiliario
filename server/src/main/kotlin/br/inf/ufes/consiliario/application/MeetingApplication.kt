package br.inf.ufes.consiliario.application

import br.inf.ufes.consiliario.dto.MeetingRegisterDto
import br.inf.ufes.consiliario.repository.MeetingRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class MeetingApplication(
    private val meetingRepository: MeetingRepository
) {
    suspend fun getMeeting(meetingId: UUID) = meetingRepository.findById(meetingId)

    suspend fun registerMeeting(meetingRegisterDto: MeetingRegisterDto) =
        meetingRepository.save(meetingRegisterDto.toEntity())

    suspend fun getAllFromTeacher(teacherId: UUID) = meetingRepository.findByTeacher(teacherId)
}